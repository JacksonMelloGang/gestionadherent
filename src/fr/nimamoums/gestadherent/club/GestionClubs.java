package fr.nimamoums.gestadherent.club;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GestionClubs {

    private static List<Club> clubList = new ArrayList<>();


    public static List<Club> getClubs() {
        return clubList;
    }

    /**
     *
     * Add Club into clubList
     * Auto check if the id is not already used, if yes then set the id to the next available id
     * **/
    public static void addClub(Club club) {
        int latestclubid = 1;

        if(clubList.size() > 0){
            latestclubid = clubList.size()+1;
        }

        club.setClubId(latestclubid);
        clubList.add(club);
    }

    // remove club from clubList
    public static void removeClub(Club club) {
        clubList.remove(club);
    }

    // edit club from clubList
    public static void editClub(Club club) {
        for(Club c : clubList){
            if(c.getClubId() == club.getClubId()){
                c.setClubNom(club.getClubNom());
                c.setClubAdresse(club.getClubAdresse());
                c.setClubContact(club.getClubContact());
                c.setClubTel(club.getClubTel());
                c.setClubMail(club.getClubMail());
                c.setClubSite(club.getClubSite());
            }
        }
    }

    public static Collection<Club> search(String searchby, String search) {
        List<Club> foundochurence = new ArrayList<>();
        switch (searchby) {
            case "nom":
                for (Club club : clubList) {
                    if (club.getClubNom().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
            case "adresse":
                for (Club club : clubList) {
                    if (club.getClubAdresse().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
            case "contact":
                for (Club club : clubList) {
                    if (club.getClubContact().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
            case "tel":
                for (Club club : clubList) {
                    if (club.getClubTel().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
            case "mail":
                for (Club club : clubList) {
                    if (club.getClubMail().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
            case "site":
                for (Club club : clubList) {
                    if (club.getClubSite().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
        }


        return null;
    }

    public static Club getClubsByName(String clubname) {
        for(Club club : clubList){
            if(club.getClubNom().equals(clubname)){
                return club;
            }
        }
        return null;
    }

    public static Club getClubByName(String entity) {
        Club clubresult = null;
        for(Club club : clubList){
            if(club.getClubNom().equals(entity)){
                clubresult = club;
            }
        }
        return clubresult;
    }

    public static Club getClubById(int clubId) {
        Club clubresult = null;
        for(Club club : clubList){
            if(club.getClubId() == clubId){
                clubresult = club;
            }
        }
        return clubresult;
    }

    public Club getClubByIndex(int clubId){
        Club club = null;
        int i = 0;
        while(i < clubList.size() && club == null){
            if(clubList.get(i).getClubId() == clubId){
                club = clubList.get(i);
            }
            i++;
        }

        return club;
    }

}
