package taxi.dao;

import taxi.domain.Operator;
import java.util.List;

public interface OperatorDao {

    Long create(Operator operator);
    Operator read(Long id);
    boolean update(Operator operator);
    boolean delete(Operator operator);
    List<Operator> findAll();
    boolean checkLogin(String login);
    boolean checkID(Long id);
    Operator findByLogin(String login);
}
