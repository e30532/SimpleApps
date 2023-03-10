package simple.dynacache;

import com.ibm.websphere.command.CacheableCommandImpl;

public class MyCommand extends CacheableCommandImpl {
    public long price;
    public String symbol = null;

    public MyCommand() {
    }

    public MyCommand(String symbol) {
        this();
        this.symbol = symbol;
    }

    public boolean isReadyToCallExecute() {
        return symbol != null;
    }

    public void performExecute() throws Exception {
        price = System.currentTimeMillis();
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public long getPrice() {
        return price;
    }
}