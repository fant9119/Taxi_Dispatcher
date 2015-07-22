package taxi.service;

import taxi.domain.Client;
import taxi.exception.OrderException;
import java.util.List;

public interface ClientService {

    boolean createClient(String name, String surname, String phone, String address) throws OrderException;
    List showClientsByPortion(int portionSize);
    List showClientsByPortion(int portionSize, int start);
    List showClientsGtSum(long sum);
    List showClientsLastMonth();

    Client findClient(String name, String surname, String phone);
    boolean update(Client client);

    Client read(Long clientId);
}
