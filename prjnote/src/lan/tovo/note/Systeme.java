package lan.tovo.note;

public class Systeme {
	private static String nomOS=System.getProperty("os.name");
	private static String versionOS=System.getProperty("os.version");
	private static String separateur=System.getProperty("file.separator");
	private static String nomUtilisateur=System.getProperty("user.name");
	private static String repertoireCourant=System.getProperty("user.dir");
	
	public static String getNomOS() {
		return nomOS;
	}
	public static String getVersionOS() {
		return versionOS;
	}
	public static String getSeparateur() {
		return separateur;
	}
	public static String getNomUtilisateur() {
		return nomUtilisateur;
	}
	public static String getRepertoireCourant() {
		return repertoireCourant;
	}
	
}
