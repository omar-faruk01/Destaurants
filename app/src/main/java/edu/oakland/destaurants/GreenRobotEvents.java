package edu.oakland.destaurants;

import com.amazonaws.mobile.client.AWSMobileClient;

public class GreenRobotEvents {
    public static class MobileClientEvent {

        public final AWSMobileClient awsMobileClient;

        public MobileClientEvent(AWSMobileClient mobileClient) {
            this.awsMobileClient = mobileClient;
        }
    }
}

