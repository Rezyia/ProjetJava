package Controlers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import Models.*;

public class Toolbox {

	/**
     * Méthode pour arrondir au quart d'heure un LocalDateTime
     * @param t Le temps qu'on veut arrondir
     * @return tqh Le temps arrondi au quart d'heure
     */
    public static LocalDateTime roundToNearestQuarter(LocalDateTime t) {
		int diff = t.getMinute()%15;
		LocalDateTime tqh = null;
		if(diff < 8) {
			tqh = t.minusMinutes(diff);
		}else if(diff >= 8) {
			tqh = t.plusMinutes(15-diff);
		}
		tqh = tqh.minusSeconds(tqh.getSecond());
		tqh = tqh.minusNanos(tqh.getNano());
		return tqh;
	}
    
    
    /**
     * 
     * @param c : controler to generate employees in
     * @param nbToGenerate : integer number of employees to generate
     */
    public static void generateEmployees(ControlerMain c, int nbToGenerate) {
    	ArrayList<Employee> list = new ArrayList<Employee>();
    	int rand;
    	
    	for (int i=0; i<nbToGenerate; i++) {
    		rand = new Random().nextInt();
    		list.add(new Employee(null, null, null));
    	}
    }
    
    
    public static String[] getRandomString() {
		String[] items = {
				"oZmzktQwhZ",
				"BYhIseRLUU",
				"dsRDzaEXco",
				"XNqRFeUNOK",
				"VZidDSletD",
				"HrxeewKEIY",
				"NseyZDklcs",
				"uBsLgSLSfj",
				"LBPGRHHEZA",
				"xLvrztlAVH",
				"uZTKpMucbz",
				"tEjTGuwWIk",
				"QtcjqMhlvD",
				"CZoVzDvidP",
				"mpIPHyRgFf",
				"eWHbmxijKz",
				"UTUBVLIkzk",
				"iNPgEHxeHC",
				"kjuaPVJiXv",
				"TLTnMmrUwQ",
				"OmtceVpJgV",
				"rzVcXBPXbr",
				"kWVvxRSOfe",
				"TvckNzbAGb",
				"zQqMKewUOb",
				"fOXgXvIOVg",
				"oBgOqoblRk",
				"ksptgjyzRA",
				"ZRrirTTEwI",
				"KQRzSrrLNe",
				"JbQNrJBYAe",
				"GmajMSYTrZ",
				"LJaTtjPTjp",
				"qsukQkAJge",
				"IGKPadIpiM",
				"rkFXKHFBur",
				"PnYUEqveXj",
				"YnhlUnrxjg",
				"GJehJVrVFW",
				"GMvNTLrkOj",
				"LfaJLtlwFA",
				"PFAHnLxDtf",
				"SSJnoEMAPC",
				"UnvLhNHVwf",
				"JBYdwSUqcT",
				"brDSVoldzn",
				"XOkpuhdMCx",
				"MRjrICfuuV",
				"MIpjWfRTOW",
				"DhEQeAyecA",
				"TSQjcYwxSt",
				"bldUGljZbb",
				"FtSeBFTAAj",
				"jUoNnDxPFI",
				"nWIHBfTafO",
				"GrOlvXnnNG",
				"kiLrEXVAxP",
				"bCZyRhuWZN",
				"IDtQztlGuo",
				"OejJIoOLVS",
				"GlnwUUWozg",
				"fQKansvlPV",
				"vCzyJarAyP",
				"NrRGpIUAuf",
				"uOZJrUnxZx",
				"jhwiVSeMQg",
				"LNACPbkLVf",
				"EeFDSrEJZU",
				"EjRLAJLumE",
				"luSyEDmQgP",
				"sxrfBvvvgK",
				"rqRcDFUhWq",
				"pemFEXnabW",
				"rnzFAnoxyG",
				"CbsgDCBYmk",
				"sutOjvrmIO",
				"psvTzXQMCn",
				"FiijCNPmui",
				"qEaOHgfrtH",
				"UYTFJiWeZL",
				"nQcQiYhHGX",
				"aLXBfQidvd",
				"wOjBxXbzHD",
				"qKEZwEziBF",
				"glXulxYJXP",
				"qajIWXJVwJ",
				"AwVoPKAgnJ",
				"Lfuvrtjkbp",
				"ocMRdftAqc",
				"jqGXwLewIu",
				"knUJhXRjHx",
				"vdpfvBfIiF",
				"WrnsPbuCYj",
				"rmgOAbgKdj",
				"NNXdSxIjfu",
				"vKsHvEQpsP",
				"RKLgdTOJeZ",
				"TsHGIZGUOL"
		};
		return items;
    }
    
    public static ArrayList<LocalTime> getAllTimeBefore(LocalTime time) {
    	LocalTime t = LocalTime.of(0, 0);
    	ArrayList<LocalTime> liste = new ArrayList<LocalTime>();
    	while(t.isBefore(time)) {
    		liste.add(t);
    		t = t.plusMinutes(15);
    	}
    	return liste;
    }
    
    public static ArrayList<LocalTime> getAllTimeAfter(LocalTime time) {
    	LocalTime t = LocalTime.of(23, 45);
    	ArrayList<LocalTime> liste = new ArrayList<LocalTime>();
    	while(time.isBefore(t)) {
    		liste.add(time);
    		time = time.plusMinutes(15);
    	}
    	liste.add(LocalTime.of(23, 45));
    	return liste;
    }
    
    public static void main(String[] args) {
    	System.out.println(getAllTimeBefore(LocalTime.of(12, 0)));
    	System.out.println(getAllTimeAfter(LocalTime.of(12, 0)));
    }
}
