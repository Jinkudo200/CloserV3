package ma.ac.emi.ginfo.closer.services;

import lombok.Getter;
import lombok.Setter;
import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Position;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class PositionService {

    public static Position current;


    public PositionService() {
    }




    public Double calculateDistanceInMeters(Position p1 , Position p2) {

        double dist = org.apache.lucene.util.SloppyMath.haversinKilometers(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude());
        return dist;
    }







}
