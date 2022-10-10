import model.Participant;
import service.FileService;
import service.ParticipantService;
import sign.Sign;
import sign.factory.SignFactory;
import service.SignService;

import java.nio.file.Path;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String USER_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {

        var time = System.currentTimeMillis();

        FileService fileService = new FileService();
        SignService signService = new SignService();
        ParticipantService participantService = new ParticipantService();

        Path groupFile = Path.of(USER_DIR, "files", "azerbaijao.txt");

        fileService.write(groupFile, participantService.findAll());

        List<String> fileRead = fileService.readLines(groupFile);
        //PARALLEL
        fileRead.stream().parallel().forEach(s -> {
            //System.out.println("NOME THREAD read: " + Thread.currentThread().getName());
            List<String> list = Arrays.asList(s.split(";"));
            participantService.saveParticipant(new Participant(list.get(0), list.get(1), LocalDateTime.parse(list.get(2), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        });

        List<Participant> participants = participantService.getParticipants();
        // PARALLEL
        participants.stream().parallel().forEach(participant -> {
            //System.out.println("NOME THREAD write: " + Thread.currentThread().getName());
            LocalDateTime localDateTime = participant.getDate();

            int age = signService.getAge(localDateTime);
            boolean isLeapYear = signService.isLeapYear(localDateTime);
            String formattedDate = signService.format(localDateTime);
            String timeZone = String.valueOf(signService.timeZone(localDateTime, participant.getZone()));
            Sign sign = new SignFactory().create(MonthDay.of(localDateTime.getMonth(), localDateTime.getDayOfMonth()));
            String risingSign = sign.getRisingSign(localDateTime.toLocalTime());

            Path participantFile = Path.of(USER_DIR, "files", participant.getName().replaceAll(" ", "_") + ".txt");
            String info = "Name: " + participant.getName() + "\n" +
                    "Zone: " + participant.getZone() + "\n" +
                    "TimeZone: " + timeZone + "\n" +
                    "Age: " + age + "\n" +
                    "Leap Year: " + isLeapYear + "\n" +
                    "Birth Date: " + formattedDate + "\n" +
                    "Sign: " + sign + "\n" +
                    "Rising Sign: " + risingSign + "\n";

            fileService.write(participantFile, info);
        });

        System.out.println(System.currentTimeMillis() - time);
    }
}