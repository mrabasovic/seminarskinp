/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;


public interface Operation {
    
    public static final int LOGIN = 0;

    public static final int ADD_ADMINISTRATOR = 1;
    public static final int DELETE_ADMINISTRATOR = 2;
    public static final int EDIT_ADMINISTRATOR = 3;
    public static final int GET_ALL_ADMINISTRATOR = 4;
    
    public static final int ADD_KLIJENT = 5;
    public static final int DELETE_KLIJENT = 6;
    public static final int EDIT_KLIJENT = 7;
    public static final int GET_ALL_KLIJENT = 8;
    
    public static final int ADD_VODIC = 9;
    public static final int DELETE_VODIC = 10;
    public static final int EDIT_VODIC = 11;
    public static final int GET_ALL_VODIC = 12;
    
    public static final int GET_ALL_HOTEL = 13;
    
    public static final int GET_ALL_TIP_PREVOZA = 14;
    
    public static final int ADD_ARANZMAN = 15;
    public static final int EDIT_ARANZMAN = 16;
    public static final int GET_ALL_ARANZMAN = 17;
    
    public static final int ADD_TERMIN = 18;
    public static final int EDIT_TERMIN = 19;
    public static final int GET_ALL_TERMIN = 20;
    public static final int DELETE_TERMIN = 21;
    
}
