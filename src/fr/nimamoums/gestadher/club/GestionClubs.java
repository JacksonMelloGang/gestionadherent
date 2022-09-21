package fr.nimamoums.gestadher.club;

import java.util.ArrayList;
import java.util.List;

public class GestionClubs {

    List<Club> clubList = new ArrayList<>();

    public List<Club> getClubList() {
        return clubList;
    }

    public void addClub(Club club) {
        clubList.add(club);
    }

    public void removeClub(Club club) {
        clubList.remove(club);
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

    public static boolean savelistintoxmlfile(){

            // create a club.xml file and convert list into xml content and save it into file



            return false;
    }

}
