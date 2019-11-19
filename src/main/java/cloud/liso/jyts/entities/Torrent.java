package cloud.liso.jyts.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Torrent {
    private final String url;
    private final String hash;
    private final String quality;
    private final String type;
    private final int seeds;
    private final int peers;
    private final long size;
    private final LocalDateTime uploaded;

    public String magnet() {
        return "magnet:?xt=urn:btih:" +
                hash +
                "&dn=" +
                url +
                "&tr=http://track.one:1234/announce&tr=udp://track.two:80";
    }
}
