package bidproduct;

import java.util.List;

public interface BidProductDAO {
    public void insert(BidProductVO bidProductId);

    public void update(BidProductVO bidProductId);

    public void delete(Integer bidProductId);

    public BidProductVO findByPrimaryKey(Integer bidProductId);

    public List<BidProductVO> getAll();

    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //public List<BidProductVO> getAll(Map<String, String[]> map);
}
