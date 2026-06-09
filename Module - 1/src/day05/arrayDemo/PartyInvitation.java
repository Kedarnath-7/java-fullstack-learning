package day05.arrayDemo;

import java.util.Scanner;

public class PartyInvitation {
    public static void main(String[] args) {
        String guests[] = new String[5];
        System.out.println("Enter 5 guests names: ");
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<guests.length;i++)
        {
            guests[i] = sc.nextLine();
        }

        System.out.println("Enter a name to check invitation: ");
        String checkGuest = sc.nextLine();
        boolean check = false;
        for(String guest: guests){
            if(guest.equals(checkGuest)){
                check = true;
                break;
            }
        }
        try{
            if(!check){
                throw new NotInvitedException();
            }
            System.out.println("Welcome to the party...." + checkGuest);
        }catch(NotInvitedException e){
            System.out.println(e.getMessage());
        }

    }
}
