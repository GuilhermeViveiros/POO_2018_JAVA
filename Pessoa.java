public class Pessoa{

	private Entidade info;
	private int nrDAF; // número de dependentes do agregado familiar
	private int nrFAF; // números fiscais do agregado familiar
	private float cF; // coeficiente fiscal
	private String cAE; // códigos das atividades economicas

	public Pessoa(){ // cria uma pessoa do ponto zero(DEFAULT)

		this.info = new Entidade();
		this.nrDAF = 0;
		this.nrFAF = 0;
		this.cF = 0;
		this.cAE = "Indefinido";

	}
	
	// cria uma pessoa ao receber todos os dados necessários

	public Pessoa(long ni_P, String nom_P, String mai_P, String morad_P, int nrDAF_P, int nrFAF_P, float cF_P, String cAE_P){

		this.info = new Entidade(ni_P, nom_P, mai_P, morad_P);
		this.nrDAF = nrDAF_P;
		this.nrFAF = nrFAF_P;
		this.cF = cF_P;
		this.cAE = cAE_P;
	}

	// cria uma pessoa a partir de outra

	public Pessoa(Pessoa iD){
		this.info = iD.getInfo();
		this.nrDAF = iD.getNrDAF();
		this.nrFAF = iD.getNrFAF();
		this.cF = iD.getCF();
		this.cAE = iD.getCAE();
	}

	// getters

	public int getNrDAF(){
		return this.nrDAF;
	}

	public int getNrFAF(){
		return this.nrFAF;
	}

	public float getCF(){
		return this.cF;
	}

	public String getCAE(){
		return this.cAE;
	}

	private Entidade getInfo(){
		return this.info;
	}
}