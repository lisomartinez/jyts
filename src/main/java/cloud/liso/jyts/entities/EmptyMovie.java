package cloud.liso.jyts.entities;

import java.util.ArrayList;

public class EmptyMovie extends Movie {
    public EmptyMovie() {
        super(0, "title", 0, 0.0, 0, new ArrayList<>(), "", "", new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Movie Not Found!!!";
    }
}
