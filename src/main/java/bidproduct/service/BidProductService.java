package bidproduct.service;

import bidproduct.model.BidProductDAO;
import bidproduct.model.BidProductDAOImpl;
import bidproduct.model.BidProductVO;

import java.sql.Date;
import java.util.List;

public class BidProductService {

    private BidProductDAO dao;

    public BidProductService() {
        dao = new BidProductDAOImpl();
    }

    public BidProductVO addBidProduct(String name, int sellerId, int categoryId, String description, int startPrice, Date startTime, Date endTime, int reviewStatus, int bidStatus) {
        BidProductVO bidProductVO = new BidProductVO();
        bidProductVO.setName(name);
        bidProductVO.setSellerId(sellerId);
        bidProductVO.setCategoryId(categoryId);
        bidProductVO.setDescription(description);
        bidProductVO.setStartPrice(startPrice);
        bidProductVO.setStartTime(startTime);
        bidProductVO.setEndTime(endTime);
        bidProductVO.setReviewStatus(reviewStatus);
        bidProductVO.setBidStatus(bidStatus);
        dao.insert(bidProductVO);
        return bidProductVO;
    }

    public BidProductVO updateBidProduct(int bidProductId, String name, int sellerId, int categoryId, String description, int startPrice, Date startTime, Date endTime, int reviewStatus, int bidStatus) {
        BidProductVO bidProductVO = new BidProductVO();
        bidProductVO.setBidProductId(bidProductId);
        bidProductVO.setName(name);
        bidProductVO.setSellerId(sellerId);
        bidProductVO.setCategoryId(categoryId);
        bidProductVO.setDescription(description);
        bidProductVO.setStartPrice(startPrice);
        bidProductVO.setStartTime(startTime);
        bidProductVO.setEndTime(endTime);
        bidProductVO.setReviewStatus(reviewStatus);
        bidProductVO.setBidStatus(bidStatus);
        dao.update(bidProductVO);
        return bidProductVO;
    }

    public void deleteBidProduct(Integer bidProductId) {
        dao.delete(bidProductId);
    }

    public BidProductVO getOneBidProduct(Integer bidProductId) {
        return dao.findByPrimaryKey(bidProductId);
    }

    public List<BidProductVO> getAll() {
        return dao.getAll();
    }
}
