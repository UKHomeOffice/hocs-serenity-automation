package config;

public enum Topics {

    DCUMIN("Cardiff University Kittens"),
    DCUTRO("Some Future DCUTRO Topic"),
    DCUN10("Some Future DCUN10 Topic");

    private final String topic;

    Topics(String topic) {
        this.topic = topic;
    }

    public String getTopic() { return topic; }

}
