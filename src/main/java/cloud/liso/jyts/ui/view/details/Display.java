package cloud.liso.jyts.ui.view.details;

import cloud.liso.jyts.entities.Movie;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import lombok.Data;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Data
public class Display {
    private final Movie movie;
    private StringBuilder st;
    @Override
    public String toString() {
        if (st == null) {
            st = new StringBuilder();
            st.append("Title: ").append(movie.getTitle()).append("\n")
                    .append("Year: ").append(movie.getYear()).append("\n")
                    .append("Genres: ").append(String.join(", ", movie.getGenres())).append("\n")
                    .append("Rating: ").append(movie.getRating()).append("\n")
                    .append("Description: ").append(movie.getDescription()).append("\n");
        }
        return st.toString();
    }

    public void print(TextGraphics text, int row, int column, int width) {
        final String title = "Title: ";
        final String genres = "Genres: ";
        final String rating = "Rating: ";
        final String description = "Description: ";

        int actual = row;
        printLine(text, actual, column, title, movie.getTitle(), TextColor.ANSI.RED);

        actual++;
        printLine(text, actual, column, genres, String.join(", ", movie.getGenres()), TextColor.ANSI.WHITE);

        actual++;
        printLine(text, actual, column, rating, String.valueOf(movie.getRating()), TextColor.ANSI.WHITE);

        actual++;
        setTitleColor(text, TextColor.ANSI.RED);
        text.putString(column, actual, description);
        setNormalColor(text, TextColor.ANSI.WHITE);
        int length = movie.getDescription().length();
        int index = 0;
        int lineNumber = 0;
        int realWidth = width - description.length();
        List<String> strings = new ArrayList<>();
        while (index + realWidth < length) {
            String line = movie.getDescription().substring(index, index + realWidth);
            index += realWidth;
            text.putString(column + description.length(), actual + lineNumber, line);
            lineNumber++;
        }


//        strings.add("Title: " + movie.getTitle());
//        strings.add("Genres: " + String.join(",", movie.getGenres()));
//        strings.add("Rating: " + movie.getRating());
//        int length = movie.getDescription().length();
//        int index = 0;
//        while (index + i < length) {
//            strings.add(movie.getDescription().substring(index, index + i));
//            final int sub;
//
//            index += i;
//        }
//        strings.add("Description: " + movie.getDescription());
//        IntStream.range(0, print.size()).forEach(i -> textGraphics.putString(column, row + i, print.get(i)));
//        return strings;
    }

    private void printLine(TextGraphics text, int row, int column, String str, String join, TextColor.ANSI color) {
        setTitleColor(text, TextColor.ANSI.RED);
        text.putString(column, row, str);
        setNormalColor(text, color);
        if (str.equals("Title: ")) {
            text.setModifiers(EnumSet.of(SGR.BOLD));
        }
        text.putString(column + str.length(), row, join);
    }

    private void setTitleColor(TextGraphics text, TextColor.ANSI red) {
        text.setForegroundColor(red);
        text.setModifiers(EnumSet.of(SGR.BOLD));
    }

    private void setNormalColor(TextGraphics text, TextColor.ANSI color) {
        text.clearModifiers();
        text.setForegroundColor(TextColor.ANSI.WHITE);
    }


    //    public String print() {
//        if (ansi == null) {
//            ansi = ansi().eraseScreen().fg(Color.RED).a("Title: ").bold().fg(Color.YELLOW).a(movie.getTitle()).a("\n").reset()
//                    .fg(Color.RED).a("Year: ").fg(Color.WHITE).a(movie.getYear()).a("\n")
//                    .fg(Color.RED).a("Genres: ").fg(Color.WHITE).a(String.join(", ", movie.getGenres())).a("\n")
//                    .fg(Color.RED).a("Rating: ").fg(Color.WHITE).a(movie.getRating()).a("\n")
//                    .fg(Color.RED).a("Description: ").fg(Color.WHITE).a(movie.getDescription()).a("\n")
//                    .bold().fg(Color.BLUE).a("Torrents: \n")
//                    .a(IntStream.range(0, movie.getTorrents().size())
//                    .mapToObj(i -> ansi().bold().fg(DEFAULT).a("\t").a("(").fg(WHITE).a((char) (i + 97)).fg(DEFAULT).a(") ")
//                            .reset()
//                            .a(movie.getTorrents().get(i).getQuality()).a(" ")
//                            .reset().toString())
//                    .collect(Collectors.joining("\n")))
//                    .reset();
//        }
//        return ansi.toString();
//    }
}
