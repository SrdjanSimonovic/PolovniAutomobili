package metode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Automobil;

public class MetodeJdbc {

	public static Connection konektujSe(String imeBaze) throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306/"+imeBaze;
		String username = "root";		
		String password = "root";
		
		return DriverManager.getConnection(url, username, password);
		
	}
	
	public static void azurirajPoMarki(int marka,int id) {
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		
		try {
			konekcija = konektujSe("polovniautomobili");
			System.out.println("Uspesna konekcija.");
			
			String query = "UPDATE automobili SET marka=? WHERE id_automobili=? ";
			pst = konekcija.prepareStatement(query);
			pst.setInt(1, marka);
			pst.setInt(2, id);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Neuspesna konekcija.");
		}finally{
			
			if(pst!=null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
			if(konekcija!=null) {
			try {
				konekcija.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
			
			
		}
		
		
	}
	
	public static void ubaciUTabeluAutomobili(double cena, int marka, int godiste, int broj_vrata, int pogon, int region, int klima, int menjac) {
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		
		try {
			konekcija = konektujSe("polovniautomobili");
			String query ="INSERT INTO automobili VALUE (null,?,?,?,?,?,?,?,?)";
			pst = konekcija.prepareStatement(query);
			pst.setDouble(1, cena);
			pst.setInt(2, marka);
			pst.setInt(3, godiste);
			pst.setInt(4, broj_vrata);
			pst.setInt(5, pogon);
			pst.setInt(6, region);
			pst.setInt(7, klima);
			pst.setInt(8, menjac);
			
			pst.execute();
			System.out.println("Uspesno ste uneli podatke u tabelu.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Neuspesna konekcija.");
		}finally {
			
			if(pst!= null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(konekcija!= null) {
				try {
					konekcija.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static String dajMarkuAutomobila(int id) {
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		String vracenaMarka = null;
		
		try {
			
		  konekcija = konektujSe("polovniautomobili");
		  String query ="SELECT marka FROM automobili WHERE id_automobili=?";
		  pst = konekcija.prepareStatement(query);
		  pst.setInt(1, id);
		  res = pst.executeQuery();
		  
		  while(res.next()) {
			  vracenaMarka = res.getString("marka");
		  }
			
			return vracenaMarka;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			
			if(res!=null) {
				try {
					res.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(konekcija!=null) {
			   try {
				konekcija.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	    }
				
	  }
		
	public static List<String> dajCeneAutomobila(int id){
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		List <String> cenaAutomobila = new ArrayList<String>();
		
		try {
			konekcija = konektujSe("polovniautomobili");
			String query = "SELECT cena FROM automobili where id>?";
			pst = konekcija.prepareStatement(query);
			pst.setInt(1, id);
			res = pst.executeQuery();
			
			while(res.next()) {
				cenaAutomobila.add("cena");
			}
			
			return cenaAutomobila;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			
			if(res!=null) {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
            if(pst!= null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }
            if(konekcija!=null) {
			try {
				konekcija.createStatement().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           }
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	public static Automobil dajAutomobil(int id) {
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		Automobil automobil = new Automobil();
		
		try {
			konekcija = konektujSe("skola_programiranja");
			String query = "SEELECT * FROM automobili WHERE id = ?";
			pst = konekcija.prepareStatement(query);
			pst.setInt(1, id);
			res = pst.executeQuery();
			
			while(res.next()) {
				automobil.setIdAutomobila(res.getInt("id_automobili"));
				automobil.setCena(res.getDouble("cena"));
				automobil.setBroj_vrata(res.getInt("broj_vrata"));
				automobil.setGodiste(res.getInt("godiste"));
				automobil.setKlima(res.getInt("klima"));
				automobil.setMarka(res.getInt("marka"));
				automobil.setMenjac(res.getInt("menjac"));
				automobil.setPogon(res.getInt("pogon"));
				automobil.setRegion(res.getInt("region"));
				
			}
			
			return automobil;
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			if(res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
			if(pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			if(konekcija != null) {
			try {
				konekcija.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 }
		
	}
		
	public static List<Automobil> dajSveAutomobile() {
		
		Connection konekcija = null;
		PreparedStatement pst= null;
		ResultSet res = null;
		List <Automobil> listaAutomobila = new ArrayList<Automobil>();
		
		try {
			konekcija = konektujSe("polovniautomobili");
			String query = "SELECT * FROM automobili";
			pst = konekcija.prepareStatement(query);
			res = pst.executeQuery();
			
			while(res.next()) {
				Automobil a = new Automobil();
				
				a.setIdAutomobila(res.getInt("id_automobili"));
				a.setBroj_vrata(res.getInt("broj_vrata"));
				a.setCena(res.getDouble("cena"));
				a.setGodiste(res.getInt("godiste"));
				a.setKlima(res.getInt("klima"));
				a.setMarka(res.getInt("marka"));
				a.setMenjac(res.getInt("menjac"));
				a.setPogon(res.getInt("pogon"));
				a.setRegion(res.getInt("region"));
				
				listaAutomobila.add(a);
			}
			
			return listaAutomobila;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			
			if(res != null) {
			try {
				res.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			}
			if(pst != null) {
			try {
				pst.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			if(konekcija != null) {
			try {
				konekcija.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	  }
		
	}
		
	
	
	}
	
	
	

