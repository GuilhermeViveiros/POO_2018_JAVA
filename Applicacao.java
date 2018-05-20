import java.io.IOException;
import java.util.Scanner;

public class Applicacao {

    public int main() {

        JavaFac estado=null;

        switch (menuEstado()) {
        case 0:
            return 1;
        case 1:
            estado = new JavaFac(menuPassword());
            break;
        case 2:
            estado = menuRecuperarEstado();
            break;
        }
        
        if(estado == null) return 0;
        
        switch (menuUser()) {
        case 0:
            return main();
        case 1:
            return menuAdmnistrador(estado);
        case 2:
            return menuContribuinte(estado);
        case 3:
            return menuEmpresa(estado);
        }
        return 1;
    }

    public int menuEstado() {
        Scanner s = new Scanner(System.in);
        int value;
        do {
            System.out.println(" Indique o número da opção que pretende expandir :");
            System.out.println(" 1 - Iniciar um novo estado ");
            System.out.println(" 2 - Continuar um estado guardado ");
            System.out.println(" 0 - Sair ");
            value = s.nextInt();
        } while (value < 1 && value > 2);
        return value;
    }

    public JavaFac menuRecuperarEstado() {
        Scanner s = new Scanner(System.in);
        String out;
        JavaFac o = null;
        boolean b = false;
        do {
            System.out.println(" ::> Indique o nome do ficheiro Guardado <:: ");
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

    public int menuUser() {
        Scanner s = new Scanner(System.in);
        int value;
        do {
            System.out.println(" Indique o número da opção que pretende expandir :");
            System.out.println(" 1 - Entrar como admnistrador ");
            System.out.println(" 2 - Entrar como Contribuinte ");
            System.out.println(" 3 - Entrar como Empresa ");
            System.out.println(" 0 - Voltar para o Menu inicial ");

            value = s.nextInt();
        } while (value < 0 && value > 3);
        return value;
    }

    public String menuPassword() {
        Scanner s = new Scanner(System.in);
        String value;
        System.out.println(" Indique a palavra-chave :");
        return s.nextLine();
    }

    public int menuAdmnistrador(JavaFac estado) {

        boolean b;
        Scanner sc = new Scanner(System.in);
        int value;
        do {
            boolean pss;
            
            try{
                pss = !menuPassword().equals(estado.getAdminPassword());
            } catch(InvalidFieldException aa){
                return menuUser();
            }
            
            if (pss) {
                System.out.println(" A palavra-chave não está correta :");
                System.out.println(" 1 - Voltar a tentar ");
                System.out.println(" 0 - Menu anterior");
                value = sc.nextInt();
                if (value == 0)
                    return menuUser();
                else
                    b = true;
            } else {
                b = false;
            }

        } while (b);

        /**
         * 
         * Fazer coisas como admin !!!!
         * 
         * eliminar entidades;
         * 
         */
        System.out.println(" Você é o admnistrador ");
        return 1;
    }

    public int menuContribuinte(JavaFac estado) {

        Scanner s = new Scanner(System.in);
        int value;
        do {
            System.out.println(" Indique o número da opção que pretende expandir :");
            System.out.println(" 1 - Criar Contribuinte (Pessoa ) ");
            System.out.println(" 2 - Criar Contribuinte (Empresa) ");
            System.out.println(" 3 - Criar Contribuinte (Empresa Interior) ");
            System.out.println(" 4 - Aceder a um contribuinte ");
            System.out.println(" 5 - Guardar Estado atual ");
            System.out.println(" 0 - Voltar para trás ");
            value = s.nextInt();
        } while (value < 0 && value > 5);

        Contacto c;
        String pw, cw = "vazio";
        float coe;
        switch (value) {
        case 0:
            return menuUser();
        case 1:
            c = menuCriarContacto();
            pw = menuPassword();
            System.out.println(" Indique o coeficiente fiscal ");
            coe = s.nextFloat();

            try {
                if (estado.contemContribuinte(c.getNif())) {
                    System.out.println(" Indique a palavra-chave da ultima vez que acedou a sua conta ");
                    cw = s.nextLine();
                }
            } catch (InvalidFieldException aa) {
                System.out.println(" O contribuinte proporcionado não contem nif ");
                return menuContribuinte(estado);
            }

            try {
                estado.addContribuinte(new Pessoa(c, pw, coe ), cw);
            } catch (IncorrectPasswordException aa) {
                System.out.println(" O contribuinte proporcionado não contem nif ");
                return menuContribuinte(estado);
            } catch (InvalidFieldException aa) {
                System.out.println(" O contribuinte proporcionado não contem nif ");
                return menuContribuinte(estado);
            }

            return menuContribuinte(estado);

        case 2:
            c = menuCriarContacto();
            pw = menuPassword();
            return 1;
        case 3:
            c = menuCriarContacto();
            pw = menuPassword();
            return 1;
        case 4: return 1;
        case 5:
            System.out.println(" Indique o nome do ficheiro onde pretende guardar");
            try {
                estado.gravarEstado(s.nextLine());
            } catch (IOException aa) {
                System.out.println(" Ocorreu um erro. Tente outra ve");
            }
            return menuContribuinte(estado);
        }
        return 0;
    }

    public Contacto menuCriarContacto() {
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
    
    public int menuEmpresa(JavaFac o){return 1;}
}
