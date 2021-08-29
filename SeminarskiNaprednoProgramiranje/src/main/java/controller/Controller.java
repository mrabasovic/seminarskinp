package controller;

import java.util.ArrayList;

import db.DBBroker;
import domain.Hotel;
import domain.Klijent;
import domain.Termin;
import domain.TipPrevoza;
import domain.Vodic;


public class Controller {
	private static Controller instance;

    public Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

	public ArrayList<Hotel> vratiHotele() {
		return DBBroker.vratiHotele();
	}

	public ArrayList<Vodic> vratiVodice() {
		return DBBroker.vratiVodice();
	}

	public ArrayList<TipPrevoza> vratiPrevoz() {
		// TODO Auto-generated method stub
		return DBBroker.vratiPrevoz();
	}

	public ArrayList<Termin> vratiTermine() {
		// TODO Auto-generated method stub
		return DBBroker.vratiTermine();
	}

	public ArrayList<Klijent> vratiKlijente() {
		// TODO Auto-generated method stub
		return DBBroker.vratiKlijente();
	}
}
