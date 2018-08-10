package alexandre.thauvin.smarttoolbox;

public class Task {

    private String service;
    private String action;
    private String hour;

    public Task(){

    }

    public Task(String service, String action, String hour){
        this.service = service;
        this.action = action;
        this.hour = hour;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
