import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmbalseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String QUERY = "SELECT nombre, x, y FROM Test.listadoutf83";

    public List<EmbalseDTO> obtenerEmbalses() {
        return jdbcTemplate.query(QUERY, new EmbalseRowMapper());
    }

    private static class EmbalseRowMapper implements RowMapper<EmbalseDTO> {
        @Override
        public EmbalseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new EmbalseDTO(
                    rs.getString("nombre"),
                    rs.getDouble("x"),
                    rs.getDouble("y")
            );
        }
    }
}
