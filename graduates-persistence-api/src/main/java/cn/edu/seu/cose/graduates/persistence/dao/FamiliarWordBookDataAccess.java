package cn.edu.seu.cose.graduates.persistence.dao;

import cn.edu.seu.cose.graduates.persistence.model.BookedWord;
import java.util.List;

/**
 *
 * @author zc
 */
public interface FamiliarWordBookDataAccess extends WordBookDataAccess {
    
    @Override
    List<BookedWord> getWordsByUserId(long userId);
    
}
