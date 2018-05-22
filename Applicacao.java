import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Scanner;

import java.util.*;

public class Applicacao {

    public int main() {
        Scanner s = new Scanner(System.in);
        Menu mestado = new Menu(" Indique o número da opção que pretende expandir :");

        mestado.add(" Iniciar um novo estado ");
        mestado.add(" Continuar um estado guardado ");

        JavaFac estado = null;

        switch (mestado.showMenu()) {
        case 0:
            return 1;
        case 1:
            estado = new JavaFac(menuPassword());
            break;
        case 2:
            estado = menuRecuperarEstado();
            break;
        }

        if (estado == null)
            return 0;

        return menuModos(estado);
    }

    private int menuModos(JavaFac estado) {
        Scanner s = new Scanner(System.in);
        Menu muser = new Menu(" Indique o número da opção que pretende expandir :");

        muser.add(" Indique o número da opção que pretende expandir :");
        muser.add(" Entrar como admnistrador ");
        muser.add(" Entrar como Contribuinte ");
        muser.add(" Entrar como Empresa ");
        muser.add(" Guardar Estado da Aplicação ");

        switch (muser.showMenu()) {
        case 0:
            return main();
        case 1:
            return menuAdmnistrador(estado);
        case 2:
            return menuContribuinte(estado);
        case 3:
            System.out.println(" Indique o numero de identificação fiscal ");
            long value = s.nextLong();
            try {
                Entidade emp = estado.getContribuinte(new Long(value));
                if (emp instanceof Empresa)
                    return menuEmpresa(estado, (Empresa) emp);
            } catch (Exception aa) {
                return menuModos(estado);
            }
            return menuModos(estado);
        case 4:
            s = new Scanner(System.in);
            System.out.println(" Indique o nome do ficheiro onde pretende guardar");
            String filename = s.nextLine();
            try {
                estado.gravarEstado(filename);
            } catch (IOException aa) {
                System.out.println(" Ocorreu um erro. Tente outra vez");
            }
            return menuModos(estado);
        }
        return 1;
    }

    private JavaFac menuRecuperarEstado() {
        Scanner s = new Scanner(System.in);
        String out;
        JavaFac o = null;
        boolean b = false;
        do {
            System.out.println(" Indique o nome do ficheiro Guardado ");
            out = s.nextLine();
            try {
                o = JavaFac.recuperarEstado(out);
                b = false;
            } catch (IOException aa) {
                b = true;
                System.out.println(" Ficheiro não encontrado ");

            } catch (ClassNotFoundException aa) {
                b = true;
                System.out.println(" Ficheiro com a class inválida");
            }

        } while (b);

        return o;
    }

    private String menuPassword() {
        Scanner s = new Scanner(System.in);
        String value;
        System.out.println(" Indique a palavra-chave :");
        return s.nextLine();
    }

    private int menuAdmnistrador(JavaFac estado) {

        boolean b;
        Scanner sc = new Scanner(System.in);
        int value;
        Collection<? extends Entidade> gather = new ArrayList<>();
        do {
            boolean pss;

            try {
                pss = !menuPassword().equals(estado.getAdminPassword());
            } catch (InvalidFieldException aa) {
                return menuModos(estado);
            }

            if (pss) {
                System.out.println(" A palavra-chave não está correta :");
                System.out.println(" 1 - Voltar a tentar ");
                System.out.println(" 0 - Menu anterior");
                value = sc.nextInt();
                if (value == 0)
                    return menuModos(estado);
                else
                    b = true;
            } else {
                b = false;
            }

        } while (b);

        Menu madmin = new Menu(" Indique a opção que pretende explorar ");

        madmin.add(" Adicionar Atividade :");
        madmin.add(" Remover Contribuinte/Empresa ");
        madmin.add(" Apresentar Empresas que mais faturam ");
        madmin.add(" Apresentar os 10 Contribuintes com mais despesa ");
        madmin.add(" Apresentar Empresa com mais faturas ");
        madmin.add(" Adicionar uma cidade à categoria de Interior ");

        switch (madmin.showMenu()) {
        case 1:
            estado.addAtividade(menuAtividade());
            return menuAdmnistrador(estado);
        case 2:
            return menuRemover(estado);
        case 3:
            System.out.println(" Indique quantas das que mais faturam quer ver ");
            gather = estado.maisFaturam(sc.nextInt());
            break;
        case 4:
            gather = estado.maisGasta();
            break;
        case 5:
            System.out.println(" Indique quantas das com mais faturas quer ver ");
            gather = estado.maisFaturas(sc.nextInt());
            break;
        case 6:
            System.out.println(" Nome da cidade ");
            String cidade = sc.nextLine();
            System.out.println(" fração de desconto ");
            double coe = sc.nextDouble();
            EmpresaInterior.addCidade(cidade, coe);
            break;
        case 0:
            return menuContribuinte(estado);
        }
        /* Aqui apresentará collection de 3,4 e 5 */

        for (Entidade o : gather) {
            try {
                System.out.println(o.getContacto().getNif().toString() + " : " + o.getContacto().getNome());
            } catch (InvalidFieldException aa) {
                continue;
            }
        }
        return menuAdmnistrador(estado);
    }

    private int menuContribuinte(JavaFac estado) {

        Scanner s = new Scanner(System.in);
        Menu mcontr = new Menu("Indique o número da opção que pretende expandir :");

        mcontr.add(" Criar Contribuinte (Pessoa ) ");
        mcontr.add(" Criar Contribuinte (Empresa) ");
        mcontr.add(" Criar Contribuinte (Empresa Interior) ");
        mcontr.add(" Aceder a um contribuinte ");

        Contacto c;
        String pw, cw = "vazio";
        float coe;
        switch (mcontr.showMenu()) {
        case 0:
            return menuModos(estado);
        case 1:
            c = menuCriarContacto();
            pw = menuPassword();
            System.out.println(" Indique o coeficiente fiscal ");
            coe = s.nextFloat();
            Pessoa nvPess;
            try {
                nvPess = new Pessoa(c, pw, coe);
                estado.addContribuinte(nvPess);
            } catch (InvalidFieldException aa) {
                System.out.println(" O contribuinte proporcionado não contem nif ");
                return menuContribuinte(estado);
            }

            return menuPessoa(estado, nvPess);

        case 2:
            c = menuCriarContacto();
            pw = menuPassword();
            Empresa x = new Empresa();
            x.setContacto(c);
            x.setPassword(pw);
            try {
                estado.addContribuinte(x);
            } catch (InvalidFieldException aa) {
                System.out.println(" O contribuinte proporcionado não contem nif ");
                return menuContribuinte(estado);
            }
            return menuEmpresa(estado, x);
        case 3:
            c = menuCriarContacto();
            pw = menuPassword();
            EmpresaInterior y = new EmpresaInterior();
            y.setContacto(c);
            y.setPassword(pw);
            try {
                estado.addContribuinte(y);
            } catch (InvalidFieldException aa) {
                System.out.println(" O contribuinte proporcionado não contem nif ");
                return menuContribuinte(estado);
            }
            return menuEmpresa(estado, y);
        case 4:
            System.out.println(" Indique o seu nif ");
            Long nif = new Long(s.nextLong());
            if (estado.contemContribuinte(nif)) {
                try {
                    return menuAcederContribuinte(estado, estado.getContribuinte(nif));
                } catch (Exception aa) {
                    System.out.println(aa.toString());
                    return menuContribuinte(estado);
                }
            } else {
                return menuContribuinte(estado);
            }
        }
        return 0;
    }

    private Contacto menuCriarContacto() {
        Scanner s = new Scanner(System.in);

        System.out.println(" Informações ");
        System.out.print(" indique o nif : ");

        Long value = new Long(s.nextLong());
        System.out.println("");

        System.out.println(" indique o nome : ");
        String nome = s.nextLine();

        System.out.println(" indique o e-mail : ");
        String mail = s.nextLine();

        System.out.println(" indique a morada : ");
        String morada = s.nextLine();

        System.out.println(" indique o numero de telefone : ");
        String movel = s.nextLine();

        return new Contacto(value, nome, mail, morada, movel);
    }

    private Atividade menuAtividade() {
        int value;
        String nome;
        String codigo;
        boolean check;
        double a, b;
        Scanner s = new Scanner(System.in);
        Menu mfatura = new Menu(" Classe de atividades disponíveis ");

        mfatura.add(" Industria Extrativa ");
        mfatura.add(" Industria Transformadora");
        mfatura.add(" Agricultura ");
        mfatura.add(" Educacao ");

        value = mfatura.showMenu("nope");

        System.out.println(" Indique o nome da atividade ");
        nome = s.nextLine();
        System.out.println(" Indique o código da atividade ");
        codigo = s.nextLine();
        switch (value) {
        case 1:
            return (new IndustriaExtrativa(nome, codigo));
        case 2:
            System.out.println(" Indique 1 caso a área é dedusível ");
            if (s.nextInt() == 1)
                check = true;
            else
                check = false;
            System.out.println(" Indique o coeficiente de cálculo A seguido do B");
            a = s.nextDouble();
            b = s.nextDouble();
            return (new IndustriaTransformadora(nome, codigo, check, a, b));
        case 3:
            System.out.println(" Indique 1 caso a área é dedusível ");
            if (s.nextInt() == 1)
                check = true;
            else
                check = false;
            System.out.println(" Indique o coeficiente de cálculo A seguido do B");
            a = s.nextDouble();
            b = s.nextDouble();
            return (new Agricultura(nome, codigo, check, a, b));
        case 4:
            System.out.println(" Indique 1 caso a área é dedusível ");
            if (s.nextInt() == 1)
                check = true;
            else
                check = false;
            return (new Educacao(nome, codigo, check));
        }
        return null;
    }

    private int menuRemover(JavaFac estado) {
        Scanner s = new Scanner(System.in);
        long value;

        System.out.println(" Indique o Nif que pretende remover ");
        value = s.nextLong();

        if (estado.contemContribuinte(new Long(value))) {
            estado.removeContribuinte(new Long(value));
        } else {
            System.out.println(" Esse contribuinte não existe no sistema");
        }
        return menuAdmnistrador(estado);
    }

    private void menuAlterarEntidade(JavaFac estado, Entidade subject) {

        int value;
        Scanner s = new Scanner(System.in);
        String str;
        String pw = "campovazio";
        Menu mentidade = new Menu(" Selecione a opção que pretende expandir");

        mentidade.add(" Alterar Nome ");
        mentidade.add(" Alterar Morada");
        mentidade.add(" Alterar E-mail ");
        mentidade.add(" Alterar número de telfone");
        mentidade.add(" Alterar Password ");

        switch (mentidade.showMenu()) {
        case 0:
            return;
        case 1:
            System.out.println(" Indique o novo nome ");
            str = s.nextLine();
            try {
                pw = subject.getPassword();
                Contacto cont = subject.getContacto();
                cont.setNome(str);
                subject.setContacto(cont);
            } catch (Exception aa) {
                System.out.println(aa.toString());
                return;
            }
            break;
        case 2:
            System.out.println(" Indique a nova morada ");
            str = s.nextLine();
            try {
                pw = subject.getPassword();
                Contacto cont = subject.getContacto();
                cont.setMorada(str);
                subject.setContacto(cont);
            } catch (Exception aa) {
                System.out.println(aa.toString());
                return;
            }
            break;
        case 3:
            System.out.println(" Indique o e-mail");
            str = s.nextLine();
            try {
                pw = subject.getPassword();
                Contacto cont = subject.getContacto();
                cont.setMail(str);
                subject.setContacto(cont);
            } catch (Exception aa) {
                System.out.println(aa.toString());
                return;
            }
            break;
        case 4:
            System.out.println(" Indique o novo numero de telefone ");
            str = s.nextLine();
            try {
                pw = subject.getPassword();
                Contacto cont = subject.getContacto();
                cont.setTelefone(str);
                subject.setContacto(cont);
            } catch (Exception aa) {
                System.out.println(aa.toString());
                return;
            }
            break;
        case 5:
            System.out.println(" Indique o novo numero de telefone ");
            str = s.nextLine();

            try {
                pw = subject.getPassword();
                subject.setPassword(str);
            } catch (Exception aa) {
                System.out.println(aa.toString());
                menuContribuinte(estado);
                return;
            }
            break;
        }

        try {
            estado.addContribuinte(subject);
        } catch (Exception aa) {
            System.out.println(aa.toString());
        }

    }

    private int menuAcederContribuinte(JavaFac estado, Entidade subject) {

        Menu mentidade = new Menu(" Selecione a opção que pretende expandir");
        Scanner s = new Scanner(System.in);

        mentidade.add(" Calculo de Deducao fiscal");
        mentidade.add(" Obter faturas ordenadas cronológicamente ");
        mentidade.add(" Obter faturas ordenadas por valor ");
        mentidade.add(" Obter Depesa ");
        mentidade.add(" Despesa por área ");
        mentidade.add(" Alterar informação básica ");
        mentidade.add(" Alterar fatura pendente ");
        mentidade.add(" Alterar Pessoa ");

        switch (mentidade.showMenu()) {
        case 0:
            return menuContribuinte(estado);
        case 1:
            System.out.println(" Indique o Intervalo de datas que petende");
            System.out.println(" Um total  de " + subject.calculoDeducao(menuData(), menuData()));
            break;
        case 2:
            System.out.println(" Indique o Intervalo de datas que petende");
            List<Fatura> x;
            try {
                x = subject.listafaturas_Crono(menuData(), menuData());
                for (Fatura var : x) {
                    System.out.println(
                            var.getServidor().getNome() + " " + var.getDate().toString() + " " + var.getTotal());
                }
            } catch (Exception aa) {
                System.out.println(aa.toString());
            }
            break;
        case 3:
            System.out.println(" Indique o Intervalo de datas que petende");
            try {
                x = subject.listafaturas_Valor(menuData(), menuData());
                for (Fatura var : x) {
                    System.out.println(
                            var.getServidor().getNome() + " " + var.getDate().toString() + " " + var.getTotal());
                }
            } catch (Exception aa) {
                System.out.println(aa.toString());
            }
            break;
        case 4:
            System.out.println(" Indique o Intervalo de datas que petende");
            System.out.println(" Um total  de " + subject.getDespesa(menuData(), menuData()));
            break;
        case 5:
            try {
                for (Map.Entry<Atividade, Double> a : subject.getDespesaArea().entrySet()) {
                    System.out.println(a.getKey().getNomeActividade() + "   : " + a.toString());
                }
            } catch (Exception aa) {
                System.out.println(aa.toString());
            }
            break;
        case 6:
            menuAlterarEntidade(estado, subject);
            try {
                estado.addContribuinte(subject);
            } catch (InvalidFieldException aa) {
                System.out.println(aa.toString());
            }
            return menuAcederContribuinte(estado, subject);

        case 7:
            int count = 0;
            List<Fatura> fact = subject.listafaturas_Pendente();
            for (Fatura fat : fact) {
                try {
                    System.out.println(" " + count + "| " + fat.getServidor().getNome() + "| "
                            + fat.getDate().toString() + " " + fat.getTotal());
                    count++;
                } catch (Exception aa) {
                    continue;
                }
            }
            int value = s.nextInt();
            if (value < fact.size()) {
                Fatura altfac = fact.get(value);
                menuAlterarFatura(estado, subject, altfac);
                subject.addFatura(altfac);
                try {
                    estado.addContribuinte(subject);
                } catch (InvalidFieldException aa) {
                    System.out.println(aa.toString());
                }
            } else {
                System.out.println(" O indice indicado não foi a presentado ");
            }
            return menuAcederContribuinte(estado, subject);
        case 8:
            if (subject instanceof Pessoa) {
                menuPessoa(estado, (Pessoa) subject);
                try {
                    estado.addContribuinte(subject);
                } catch (InvalidFieldException aa) {
                    System.out.println(aa.toString());
                }
                return menuAcederContribuinte(estado, subject);

            } else {
                System.out.println(" Não é uma Pessoa ");
            }
            return menuAcederContribuinte(estado, subject);
        }

        return menuAcederContribuinte(estado, subject);
    }

    private LocalDate menuData() {
        int dia, mes, ano;
        Scanner s = new Scanner(System.in);
        System.out.println(" Indique o dia ");
        dia = s.nextInt();
        System.out.println(" Indique o número do mês ");
        mes = s.nextInt();
        System.out.println(" Indique o número do ano ");
        ano = s.nextInt();

        return LocalDate.of(ano, Month.of(mes), dia);
    }

    private void menuAlterarFatura(JavaFac estado, Entidade subject, Fatura f) {

        try {
            for (Atividade g : f.getHistory()) {
                try {
                    System.out.println(g.getNomeActividade() + "   " + g.getCodidigoActividade());
                } catch (Exception a) {
                    continue;
                }
            }
        } catch (Exception b) {

        }

        f.setArea(menuAtividade());
        subject.addFatura(f);
        Empresa emp;
        try {
            emp = (Empresa) estado.getContribuinte(f.getServidor().getNif());
            emp.addFatura(f);
            estado.addContribuinte(emp);
            menuAcederContribuinte(estado, subject);

        } catch (Exception aa) {
            System.out.println(aa.toString());
            menuAlterarFatura(estado, subject, f);
        }

    }

    private int menuEmpresa(JavaFac estado, Empresa ent) {
        Menu mempresa = new Menu(" Seleciona a opção que pretende explorar ");
        Scanner s = new Scanner(System.in);

        mempresa.add(" Remover o registo de uma dada Fatura ");
        mempresa.add(" Criar uma nova Fatura");
        mempresa.add(" Total Faturado ");
        mempresa.add(" Cálculo da Dedução fiscal ");
        mempresa.add(" Obter emissões por data ");
        mempresa.add(" Obter emissões por valor ");
        mempresa.add(" Ver áreas ");
        mempresa.add(" Número de Faturas emitidas ");
        mempresa.add(" Alterar informações ");
        mempresa.add(" Faturas de um dado cliente ");
        mempresa.add(" Adicionar Produtos ");
        mempresa.add(" Adicionar Atividade ");
        mempresa.add(" Empresa do Interior ");

        switch (mempresa.showMenu()) {
        case 0:
            return menuModos(estado);
        case 1:
            System.out.println(" Indique o nome do cliente que pretende remover a fatura ");
            String nome = s.nextLine();
            if (ent.contemCliente(nome)) {
                System.out.println(" Pretende ver as faturas pendentes ? (1 para Sim / 0 para Não)");
                int value = s.nextInt();
                List<Fatura> lfat;
                switch (value) {
                case 1:
                    try {
                        int count = 0;
                        lfat = ent.listarPendentes(nome);
                        for (Fatura x : lfat) {
                            System.out.println(count + x.getDate().toString() + x.getTotal());
                            count++;
                        }
                        System.out.println(" Insira o numero da fatura que pretende eliminar ");
                        value = s.nextInt();
                        if (value > 0 && value < lfat.size()) {
                            ent.removerFatura(lfat.get(value));
                        } else {
                            System.out.println(" Indice invalido ");
                            return menuEmpresa(estado, ent);
                        }
                        try {
                            estado.addContribuinte(ent);
                        } catch (InvalidFieldException aa) {
                            System.out.println(aa.toString());
                        }
                        return menuEmpresa(estado, ent);

                    } catch (EmptySetException aa) {
                        System.out.println(" O que cliente não existe ");
                        return menuEmpresa(estado, ent);
                    }
                case 0:
                    try {
                        int count = 0;
                        lfat = ent.faturasDespesa(nome);
                        for (Fatura x : lfat) {
                            System.out.println(count + x.getDate().toString() + x.getTotal());
                            count++;
                        }
                        System.out.println(" Insira o numero da fatura que pretende eliminar ");
                        value = s.nextInt();
                        if (value < lfat.size()) {
                            ent.removerFatura(lfat.get(value));
                        } else {
                            System.out.println(" Indice invalido ");
                            return menuEmpresa(estado, ent);
                        }
                        try {
                            estado.addContribuinte(ent);
                        } catch (InvalidFieldException aa) {
                            System.out.println(aa.toString());
                        }
                        return menuEmpresa(estado, ent);

                    } catch (EmptyMapException aa) {
                        System.out.println(" O que cliente não existe ");
                        return menuEmpresa(estado, ent);
                    }
                default:
                    return menuEmpresa(estado, ent);
                }

            } else {
                System.out.println(" Cliente não existente ");
                return menuEmpresa(estado, ent);
            }

        case 2:
            menuNovaFatura(estado, ent);
            try {
                estado.addContribuinte(ent);
            } catch (InvalidFieldException aa) {
                System.out.println(aa.toString());
            }
            return menuEmpresa(estado, ent);
        case 3:
            System.out.println(" Indique o Intervalo que pretende saber ");
            System.out.println(" Um total de " + ent.totalFaturado(menuData(), menuData()));
            return menuEmpresa(estado, ent);
        case 4:
            System.out.println(" Indique o Intervalo que pretende saber ");
            System.out.println(" Um total de " + ent.calculoDeducao(menuData(), menuData()));
            return menuEmpresa(estado, ent);
        case 5:

            try {
                for (Fatura fvar : ent.getEmissoesD()) {
                    try {
                        System.out.println(fvar.getCnif() + " " + fvar.getDate().toString() + " |" + fvar.getTotal());
                    } catch (InvalidFieldException aa) {
                        continue;
                    }
                }
            } catch (EmptySetException aa) {
                System.out.println(" Não tem Faturas ");
            }
            return menuEmpresa(estado, ent);
        case 6:
            try {
                for (Fatura fvar : ent.getEmissoesV()) {
                    try {
                        System.out.println(fvar.getCnif() + " " + fvar.getDate().toString() + " |" + fvar.getTotal());
                    } catch (InvalidFieldException aa) {
                        continue;
                    }
                }
            } catch (EmptySetException aa) {
                System.out.println(" Não tem Faturas ");
            }
            return menuEmpresa(estado, ent);
        case 7:
            try {
                for (Atividade avar : ent.getAreas()) {
                    try {
                        System.out.println(avar.getCodidigoActividade() + " | " + avar.getNomeActividade());
                    } catch (InvalidFieldException aa) {
                        continue;
                    }
                }
            } catch (EmptySetException aa) {
                System.out.println(" Não tem Faturas ");
            }
            return menuEmpresa(estado, ent);
        case 8:
            System.out.println(" Emitiu " + ent.numeroDeFaturasEmitidas() + " Faturas ");
            return menuEmpresa(estado, ent);
        case 9:
            menuAlterarEntidade(estado, ent);
            return menuEmpresa(estado, ent);
        case 10:
            System.out.println(" Indique o nome do cliente que pretende remover a fatura ");
            nome = s.nextLine();
            if (ent.contemCliente(nome)) {
                System.out.println(" Pretende ver as faturas pendentes ? (1 para Sim / 0 para Não)");
                int value = s.nextInt();
                List<Fatura> lfat;
                switch (value) {
                case 1:
                    try {
                        int count = 0;
                        lfat = ent.listarPendentes(nome);
                        for (Fatura x : lfat)
                            System.out.println(x.getDate().toString() + x.getTotal());

                        return menuEmpresa(estado, ent);

                    } catch (EmptySetException aa) {
                        System.out.println(" O cliente não existe ");
                        return menuEmpresa(estado, ent);
                    }
                case 0:
                    try {
                        int count = 0;
                        lfat = ent.faturasDespesa(nome);
                        for (Fatura x : lfat)
                            System.out.println(x.getDate().toString() + x.getTotal());

                        return menuEmpresa(estado, ent);
                    } catch (EmptyMapException aa) {
                        System.out.println(" O cliente não existe ");
                        return menuEmpresa(estado, ent);
                    }

                default:
                    return menuEmpresa(estado, ent);
                }

            } else {
                System.out.println(" Cliente não existente ");
                return menuEmpresa(estado, ent);
            }
        case 11:
            System.out.println(" Indique o nome do novo produto");
            nome = s.nextLine();
            System.out.println(" Indique o preço do novo produto");
            double preco = s.nextDouble();
            System.out.println(" Indique o Indice da Atividade que quer associar ao Produto ");
            try {
                int count = 0;
                List<Atividade> la = new ArrayList<Atividade>(ent.getAreas());
                for (Atividade avar : la) {
                    try {
                        System.out
                                .println(count + " " + avar.getCodidigoActividade() + " | " + avar.getNomeActividade());
                    } catch (InvalidFieldException aa) {
                        continue;
                    }
                    count++;
                }
                int indx = s.nextInt();
                Atividade avar;
                if (indx < la.size()) {
                    avar = la.get(indx);
                    ent.adicionarArtigo(new Produto(nome, avar, preco));
                } else {
                    System.out.println(" Indice muito grande ");
                    return menuEmpresa(estado, ent);
                }

                try {
                    estado.addContribuinte(ent);
                } catch (InvalidFieldException aa) {
                    System.out.println(aa.toString());
                }

            } catch (EmptySetException aa) {
                System.out.println(" Não tem Faturas ");
            }

        case 12:
            ent.adicionaArea(menuAtividade());
            try {
                estado.addContribuinte(ent);
            } catch (InvalidFieldException aa) {
                System.out.println(aa.toString());
            }
            return menuEmpresa(estado, ent);
        case 13:
            if (ent instanceof EmpresaInterior) {
                return menuEmpresaInterior(estado, (EmpresaInterior) ent);
            } else {
                System.out.println("Não é uma empresa do interior");
                return menuEmpresa(estado, ent);
            }
        }
        return menuModos(estado);
    }

    private int menuPessoa(JavaFac estado, Pessoa subject) {
        Menu mpessoa = new Menu(" Seleciona a opção que pretende explorar ");
        Scanner s = new Scanner(System.in);

        mpessoa.add(" Cálculo da Dedução fiscal ");
        mpessoa.add(" Despesa do Agregado ");
        mpessoa.add(" Ver Emprego ");
        mpessoa.add(" Alterar emprego ");
        mpessoa.add(" Alterar o coeficiente fiscal ");
        mpessoa.add(" Histórico de empregos ");

        switch (mpessoa.showMenu()) {
        case 0:
            return menuAcederContribuinte(estado, subject);
        case 1:
            System.out.println(" Indique o Intervalo de datas que petende");
            System.out.println(" Um total  de " + subject.calculoDeducao(menuData(), menuData()));

            break;
        case 2:
            System.out.println(" Indique o Intervalo de datas que petende");
            LocalDate begin = menuData(), end = menuData();
            try {
                double sm = subject.getDespesa(begin, end);
                for (Long x : subject.getAgregado()) {
                    try {
                        sm += estado.getContribuinte(x).getDespesa(begin, end);
                    } catch (Exception aa) {
                        System.out.println(aa.toString());
                    }
                }
                System.out.println(" Um total  de " + sm);
            } catch (Exception aa) {
                System.out.println(" Um total  de " + subject.getDespesa(begin, end));
            }
            break;
        case 3:
            try {
                Atividade act = subject.getEmprego();
                System.out.println(" " + act.getCodidigoActividade() + " " + act.getNomeActividade());
            } catch (Exception aa) {
                System.out.println(aa.toString());
            }
            break;
        case 4:
            subject.setEmprego(menuAtividade());
            System.out.println(" Indique o nif do seu empregador ");
            subject.setNifEmpregador(s.nextLong());

            try {
                estado.addContribuinte(subject);
            } catch (Exception aa) {
                System.out.println(aa.toString());
            }

            break;
        case 5:
            System.out.println(" Indique o novo coeficiente fiscal");
            subject.setCoeficiente(s.nextDouble());
            break;
        case 6:
            for (Atividade ac : subject.getHistorico()) {

                try {
                    System.out.println(ac.getCodidigoActividade() + "  " + ac.getNomeActividade());
                } catch (InvalidFieldException a) {
                    continue;
                }
            }
            break;
        }
        return menuPessoa(estado, subject);
    }

    private void menuNovaFatura(JavaFac estado, Empresa ent) {
        Scanner s = new Scanner(System.in);
        System.out.println(" Indique o Nif do contribuiente a quem quer emitir uma fatura ");
        long nif = s.nextLong();
        Entidade usr;
        try {
            usr = estado.getContribuinte(new Long(nif));
        } catch (Exception aa) {
            System.out.println(" Contribuinte não existente");
            return;
        }
        Menu mcompra = new Menu(" Indique o produto que pretende incluir na Fatura ");

        List<Produto> avar;
        try {
            avar = new ArrayList<>(ent.getArtigos());
        } catch (EmptySetException aa) {
            System.out.println(" A empresa não tem Artigos ");
            return;
        }

        for (Produto a : avar) {
            try {
                mcompra.add(a.getNome() + "\t| " + a.getPreco());
            } catch (Exception aa) {
                System.out.println(" Erro inesperado ");
                return;
            }
        }

        int value;
        List<Produto> compras = new ArrayList<>();

        do {
            value = mcompra.showMenu();
            compras.add(avar.get(value - 1));

        } while (value > 0);

        try {
            ent.faturaEmi(usr, compras);
        } catch (Exception aa) {
            System.out.println(aa.toString());
        }

        try {
            estado.addContribuinte(ent);
        } catch (InvalidFieldException aa) {
            System.out.println(aa.toString());
        }

    }

    private int menuEmpresaInterior(JavaFac estado, EmpresaInterior ent) {

        Menu mint = new Menu(" Indique a opção que pretende explorar ");
        mint.add(" Associar uma cidade do interior a esta empresa ");

        switch (mint.showMenu()) {
        case 0:
            return menuEmpresa(estado, ent);
        case 1:
            System.out.println(" Indique o nome :");
            Scanner nom = new Scanner(System.in);
            String cid = nom.nextLine();
            try {
                ent.setConselho(cid);

                try {
                    estado.addContribuinte(ent);
                } catch (Exception aa) {
                    System.out.println(aa.toString());
                }
            } catch (Exception aa) {
                System.out.println(" Esse conselho não faz parte do conjunto conselhos do Interior ");
            }
            break;
        }
        return menuEmpresa(estado, ent);

    }
}