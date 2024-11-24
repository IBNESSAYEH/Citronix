package com.youcode.citronix.Util;

import com.youcode.citronix.entity.enums.Saison;

import java.time.LocalDate;
import java.time.Month;

public class DeterminSaison {
    public static Saison defineSaisonFromDateRecolte(LocalDate dateRecolte) {
        Month monthRecolte = dateRecolte.getMonth();
        switch (monthRecolte) {
            case DECEMBER:
            case JANUARY:
            case FEBRUARY:
                return Saison.HIVER;
            case MARCH:
            case APRIL:
            case MAY:
                return Saison.PRINTEMPS;
            case JUNE:
            case JULY:
            case AUGUST:
                return Saison.ETE;
            case SEPTEMBER:
            case OCTOBER:
            case NOVEMBER:
                return Saison.AUTOMNE;
            default:
                throw new IllegalArgumentException("Invalid month: " + monthRecolte);
        }
    }
}
