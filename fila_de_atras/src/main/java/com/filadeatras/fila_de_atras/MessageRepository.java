package com.filadeatras.fila_de_atras;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>{
	
	List<Message> findBymessageAddressee(User messageAdressee);
	List<Message> findBymessageSender(User messageSender);
	List<Message> findBymessageRead(boolean messageRead);

}
