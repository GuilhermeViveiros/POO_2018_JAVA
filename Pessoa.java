public class Pessoa{

	private Entidade info;
	private int nrDAF;
	private int nrFAF;
	private float cF;
	private string cAE;

	public Pessoa(){

		this.info = new Entidade();
		this.nrDAF = 0;
		this.nrFAF = 0;
		this.cF = 0;
		this.cAE = "Indefinido";

	}

	public Pessoa(Entidade iD_P,int nrDAF_P, int nrFAF_P, float cF_P, string cAE_P){

		this.info = iD_P;
		this.nrDAF = nrDAF_P;
		this.nrFAF = nrFAF_P;
		this.cF = cF_P;
		this.cAE = cAE_P;
	}

	public Pessoa(Pessoa iD){

		this.nrDAF = iD.getNrDAF();
		this.nrFAF = iD.getNrFAF();
		this.cF = iD.getCF();
		this.cAE = iD.getCAE();
	}

	public int getNrDAF(){
		return this.nrDAF;
	}

	public int getNrFAF(){
		return this.nrFAF;
	}

	public float getCF(){
		return this.cF;
	}

	public string getCAE(){
		return this.cAE;
	}

	public Entidade getID(){
		return this.iD;
	}
}