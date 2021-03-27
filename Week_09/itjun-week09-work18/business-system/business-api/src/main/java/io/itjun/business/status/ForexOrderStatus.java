package io.itjun.business.status;

public enum ForexOrderStatus {
    //完成
    COMPLETE(99),
    CONSIGNMENT(0),
    HAVE_BUY(1),
    REMITTANCE(2),
    CANCEL(-1),
    ERROR(-99);
    /**
     * 状态
     */
    private int status;

    ForexOrderStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
