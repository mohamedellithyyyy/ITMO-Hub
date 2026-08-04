package database;

import models.*;
import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class MusicBandDAO {

    public List<MusicBand> loadAllBands() {
        List<MusicBand> bands = new ArrayList<>();
        String sql = "SELECT mb.*, u.username as owner_name FROM music_bands mb " +
                     "JOIN users u ON mb.owner_id = u.id";
        try (Statement stmt = DatabaseManager.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                bands.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bands;
    }

    public int insertBand(MusicBand band, int ownerId) {
        String sql = "INSERT INTO music_bands (" +
                     "name, x, y, creation_date, number_of_participants, albums_count, genre, " +
                     "frontman_name, frontman_height, frontman_eye_color, frontman_hair_color, frontman_nationality, " +
                     "location_x, location_y, location_z, location_name, owner_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseManager.getInstance().getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fillStatement(stmt, band, ownerId);
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updateBand(MusicBand band, int userId) {
        String sql = "UPDATE music_bands SET name=?, x=?, y=?, number_of_participants=?, albums_count=?, " +
                     "genre=?, frontman_name=?, frontman_height=?, frontman_eye_color=?, frontman_hair_color=?, " +
                     "frontman_nationality=?, location_x=?, location_y=?, location_z=?, location_name=? " +
                     "WHERE id=? AND owner_id=?";
        try (PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql)) {
            fillStatementWithoutOwner(stmt, band);
            stmt.setInt(16, band.getId());
            stmt.setInt(17, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBand(int bandId, int userId) {
        String sql = "DELETE FROM music_bands WHERE id=? AND owner_id=?";
        try (PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, bandId);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clearByUser(int userId) {
        String sql = "DELETE FROM music_bands WHERE owner_id=?";
        try (PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLowerBands(MusicBand band, int userId) {
        String sql = "DELETE FROM music_bands WHERE owner_id=? AND number_of_participants < ?";
        try (PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setLong(2, band.getNumberOfParticipants());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private MusicBand mapResultSet(ResultSet rs) throws SQLException {
        MusicBand band = new MusicBand();
        band.setId(rs.getInt("id"));
        band.setName(rs.getString("name"));
        band.setCoordinates(new Coordinates(rs.getDouble("x"), rs.getLong("y")));
        Timestamp ts = rs.getTimestamp("creation_date");
        if (ts != null)
            band.setCreationDate(ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault()));
        band.setNumberOfParticipants(rs.getLong("number_of_participants"));
        band.setAlbumsCount(rs.getLong("albums_count"));
        String genreStr = rs.getString("genre");
        if (genreStr != null) band.setGenre(MusicGenre.valueOf(genreStr));

        Person p = new Person();
        p.setName(rs.getString("frontman_name"));
        p.setHeight(rs.getDouble("frontman_height"));
        p.setEyeColor(rs.getString("frontman_eye_color"));
        p.setHairColor(rs.getString("frontman_hair_color"));
        p.setNationality(rs.getString("frontman_nationality"));

        Location loc = new Location();
        loc.setX(rs.getLong("location_x"));
        loc.setY(rs.getDouble("location_y"));
        loc.setZ(rs.getLong("location_z"));
        loc.setName(rs.getString("location_name"));
        p.setLocation(loc);
        band.setFrontMan(p);

        band.setOwnerId(rs.getInt("owner_id"));
        band.setOwnerUsername(rs.getString("owner_name"));
        return band;
    }

    private void fillStatement(PreparedStatement stmt, MusicBand b, int ownerId) throws SQLException {
        stmt.setString(1, b.getName());
        stmt.setDouble(2, b.getCoordinates().getX());
        stmt.setLong(3, b.getCoordinates().getY());
        stmt.setTimestamp(4, Timestamp.from(b.getCreationDate().toInstant()));
        stmt.setLong(5, b.getNumberOfParticipants());
        stmt.setLong(6, b.getAlbumsCount());
        stmt.setString(7, b.getGenre() == null ? null : b.getGenre().name());

        Person fm = b.getFrontMan();
        stmt.setString(8, fm.getName());
        stmt.setDouble(9, fm.getHeight());
        stmt.setString(10, fm.getEyeColor());
        stmt.setString(11, fm.getHairColor());
        stmt.setString(12, fm.getNationality());

        Location loc = fm.getLocation();
        if (loc != null) {
            stmt.setObject(13, loc.getX(), Types.BIGINT);
            stmt.setObject(14, loc.getY(), Types.DOUBLE);
            stmt.setObject(15, loc.getZ(), Types.BIGINT);
            stmt.setString(16, loc.getName());
        } else {
            stmt.setNull(13, Types.BIGINT);
            stmt.setNull(14, Types.DOUBLE);
            stmt.setNull(15, Types.BIGINT);
            stmt.setNull(16, Types.VARCHAR);
        }
        stmt.setInt(17, ownerId);
    }

    private void fillStatementWithoutOwner(PreparedStatement stmt, MusicBand b) throws SQLException {
        stmt.setString(1, b.getName());
        stmt.setDouble(2, b.getCoordinates().getX());
        stmt.setLong(3, b.getCoordinates().getY());
        stmt.setLong(4, b.getNumberOfParticipants());
        stmt.setLong(5, b.getAlbumsCount());
        stmt.setString(6, b.getGenre() == null ? null : b.getGenre().name());

        Person fm = b.getFrontMan();
        stmt.setString(7, fm.getName());
        stmt.setDouble(8, fm.getHeight());
        stmt.setString(9, fm.getEyeColor());
        stmt.setString(10, fm.getHairColor());
        stmt.setString(11, fm.getNationality());

        Location loc = fm.getLocation();
        if (loc != null) {
            stmt.setObject(12, loc.getX(), Types.BIGINT);
            stmt.setObject(13, loc.getY(), Types.DOUBLE);
            stmt.setObject(14, loc.getZ(), Types.BIGINT);
            stmt.setString(15, loc.getName());
        } else {
            stmt.setNull(12, Types.BIGINT);
            stmt.setNull(13, Types.DOUBLE);
            stmt.setNull(14, Types.BIGINT);
            stmt.setNull(15, Types.VARCHAR);
        }
    }
}
