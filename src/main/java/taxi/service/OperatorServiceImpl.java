package taxi.service;

import taxi.dao.OperatorDao;
import taxi.domain.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorDao operatorDao;

    public OperatorDao getClientDao() {
        return operatorDao;
    }

    public void setClientDao(OperatorDao operatorDao) {
        this.operatorDao = operatorDao;
    }

    public OperatorServiceImpl() {}


    @Override
    public List<Operator> findAllOperators() {
        return operatorDao.findAll();
    }
}
