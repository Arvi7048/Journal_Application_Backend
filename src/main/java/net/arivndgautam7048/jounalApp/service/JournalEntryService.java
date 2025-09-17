package net.arivndgautam7048.jounalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.arivndgautam7048.jounalApp.entity.User;
import org.bson.types.ObjectId;   // ✅ Correct import
import net.arivndgautam7048.jounalApp.entity.JournalEntry;
import net.arivndgautam7048.jounalApp.repository.JournalEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository ;

    @Autowired
    private UserService userService;




    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
      try{
          User user = userService.findByUserName(userName);
          journalEntry.setDate(LocalDateTime.now());

          JournalEntry saved = journalEntryRepository.save(journalEntry);
          user.getJournalEntries().add(saved);

          userService.saveUser(user); // ✅ password not touched

      }catch(Exception e){

          log.error("Error",e);
          throw new RuntimeException("An error occured while saving entry: "+e);

      }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){

        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {

        return journalEntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x-> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }

        }catch(Exception e){
            log.error("Error ",e);
            throw new RuntimeException("An error occured while saving the entry");
        }
        return removed;
    }
}
