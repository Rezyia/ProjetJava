package Controlers;

import java.time.LocalDateTime;

public class Toolbox {

	/**
     * M�thode pour arrondir au quart d'heure un LocalDateTime
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
}
