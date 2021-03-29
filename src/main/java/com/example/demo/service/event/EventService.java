package com.example.demo.service.event;

import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.model.GroupAction;
import com.example.demo.model.Wallet;
import com.example.demo.repository.IWalletRepository;
import com.example.demo.repository.EventRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EventService implements IEventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private IWalletRepository walletRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event add(Event event) {
        return null;
    }

    @Override
    public boolean remove(Event event) {
        eventRepository.delete(event);
        return true;
    }

    @Override
    public Event update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.getOne(id);
    }

    @Override
    public List<Event> search(EventDTO event) {
        System.out.println(eventRepository.getEventByCondition(event));
        return eventRepository.getEventByCondition(event);
    }

    @Override
    public Event save(Event event, String date, String action, String wallet, String money) {
        try {
            event.setWallet(entityManager.find(Wallet.class, Long.parseLong(wallet)));
            event.setMoney(Double.parseDouble(money));
            event.setGroupAction(entityManager.find(GroupAction.class, Long.parseLong(action)));
            event.setUser(event.getWallet().getAppUser());
            event.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));
            return eventRepository.save(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventRepository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event findEventByStringId(String id, String name, String note) {
        Event event = findById(Long.parseLong(id));
        event.setName(name);
        event.setNote(note);
        return event;
    }

    @Override
    public List<Event> findAllByDateBetween(Date start, Date end) {
        return eventRepository.findAllByDateBetween(start, end);
    }

    public List<Wallet> getListWallet(){
       return eventRepository.getWalletByUser();
    }

    public List<Event> getListEvents(){
        return eventRepository.getListByUser();
    }

    public ByteArrayInputStream writeToFileExcel() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Event");
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);
            Cell firstCell = firstRow.createCell(0);
            firstCell.setCellValue("Name event");
            Cell firstCell1 = firstRow.createCell(1);
            firstCell1.setCellValue("Money event");
            Cell firstCell2 = firstRow.createCell(2);
            firstCell2.setCellValue("Date event");
            Cell firstCell3 = firstRow.createCell(3);
            firstCell3.setCellValue("Wallet event");
            Cell firstCell4 = firstRow.createCell(4);
            firstCell4.setCellValue("Action event");
            List<Event> listEvents = getListEvents();
            for (Event event : listEvents) {
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(event.getName());
                Cell cell2 = row.createCell(1);
                cell2.setCellValue(event.getMoney());
                Cell cell3 = row.createCell(2);
                cell3.setCellValue(event.getDate().toString());
                Cell cell4 = row.createCell(3);
                cell4.setCellValue(event.getWallet().getName());
                Cell cell5 = row.createCell(4);
                cell5.setCellValue(event.getGroupAction().getName());
            }
            workbook.write(out);
            workbook.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
