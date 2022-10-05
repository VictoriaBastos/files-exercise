package service;

import model.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantService {

    public List<Participant> participants = new ArrayList<>();

    public String findAll() {
        return "Iuri;America/Sao_Paulo;1999-06-22 16:30\n" +
                "Tomas;America/Sao_Paulo;1993-08-30 15:30\n" +
                "Matheus;America/Sao_Paulo;1991-09-19 09:50\n" +
                "Victoria;America/Sao_Paulo;1993-08-30 14:45\n" +
                "Sogolon;America/Sao_Paulo;1992-08-01 21:21";
    }

    public void saveParticipant(Participant participant) {
        participants.add(participant);
    }

    public List<Participant> getParticipants() {
        return participants;
    }

}
