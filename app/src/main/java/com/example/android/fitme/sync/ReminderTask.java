package com.example.android.fitme.sync;

import android.content.Context;

import com.example.android.fitme.utils.NotificationUtils;
import com.example.android.fitme.utils.SharedPrefUtils;

/**
 * Created by vlad on 25.08.2017.
 */

public class ReminderTask {
    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    public static final String ACTION_CHARGING_REMINDER = "charging-reminder";

    public static void executeTask(Context context, String action) {
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)) {
            incrementWaterCount(context);
        } else if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
            NotificationUtils.clearAllNotifications(context);
        } else if (ACTION_CHARGING_REMINDER.equals(action)){
            issueChargingReminder(context);
        }
    }

    private static void issueChargingReminder(Context context) {
        SharedPrefUtils.incrementChargingReminderCount(context);
        NotificationUtils.remindUserBecauseCharging(context);
    }

    private static void incrementWaterCount(Context context) {
        SharedPrefUtils.incrementWaterCount(context);
        NotificationUtils.clearAllNotifications(context);
    }
}
