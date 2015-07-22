package taxi.service;

import taxi.dao.ClientDao;
import taxi.domain.Client;
import taxi.exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    public ClientDao getClientDao() {
        return clientDao;
    }

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public ClientServiceImpl() {}

    @Override
    @Transactional
    public boolean createClient(String name, String surname, String phone, String address) throws OrderException {
        if (name.isEmpty() || name == null) {
            throw new OrderException("The name field is empty!");
        }
        if (surname.isEmpty() || surname == null) {
            throw new OrderException("The surname field is empty!");
        }
        Pattern p = Pattern.compile("\\([0-9]{3}\\)[0-9]{3}\\-[0-9]{2}\\-[0-9]{2}");
        Matcher m = p.matcher(phone);
        if (!m.matches()) {
            throw new OrderException("The phone number format must be: (XXX)XXX-XX-XX!");
        }
        if (address.isEmpty() || address == null) {
            throw new OrderException("The address field is empty!");
        }
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setPhone(phone);
        client.setAddress(address);
        clientDao.create(client);
        return true;
    }

    @Override
    @Transactional
    public List showClientsByPortion(int portionSize) {
        return clientDao.showClientsByPortion(portionSize);
    }
    @Override
    @Transactional
    public List showClientsByPortion(int portionSize, int start) {
        return clientDao.showClientsByPortion(portionSize, start);
    }

    @Override
    @Transactional
    public List showClientsGtSum(long sum) {
        return clientDao.showClientsGtSum(sum);
    }

    @Override
    @Transactional
    public List showClientsLastMonth() {
        return clientDao.showClientsLastMonth();
    }

    @Override
    @Transactional
    public Client findClient(String name, String surname, String phone) {
        return clientDao.findClient(name, surname, phone);
    }

    @Override
    @Transactional
    public boolean update(Client client) {
        return clientDao.update(client);
    }

    @Override
    @Transactional
    public Client read(Long clientId) {
        return clientDao.read(clientId);
    }
}
