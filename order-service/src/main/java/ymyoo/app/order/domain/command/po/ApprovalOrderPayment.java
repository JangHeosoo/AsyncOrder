package ymyoo.app.order.domain.command.po;

/**
 * 결제 후 결제사로 부터 반환 받은 승인 정보
 *
 * Created by 유영모 on 2016-10-10.
 */
public class ApprovalOrderPayment {
    /** 승인 아이디 **/
    private String tid;

    public ApprovalOrderPayment() {
    }

    public ApprovalOrderPayment(String tid) {
        this.tid = tid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "ApprovalOrderPayment{" +
                "tid='" + tid + '\'' +
                '}';
    }
}
