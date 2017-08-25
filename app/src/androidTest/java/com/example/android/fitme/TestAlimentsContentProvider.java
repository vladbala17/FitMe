package com.example.android.fitme;

import android.content.UriMatcher;
import android.net.Uri;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.fitme.data.AlimentContentProvider;
import com.example.android.fitme.data.AlimentContract;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Created by vlad on 21.08.2017.
 */
@RunWith(AndroidJUnit4.class)
public class TestAlimentsContentProvider {

    //================================================================================
    // Test UriMatcher
    //================================================================================


    private static final Uri TEST_ALIMENTS = AlimentContract.AlimentEntry.CONTENT_URI;
    // Content URI for a single aliment with id = 1
    private static final Uri TEST_ALIMENTS_WITH_ID = TEST_ALIMENTS.buildUpon().appendPath("1").build();


    /**
     * This function tests that the UriMatcher returns the correct integer value for
     * each of the Uri types that the ContentProvider can handle. Uncomment this when you are
     * ready to test your UriMatcher.
     */
    @Test
    public void testUriMatcher() {

        /* Create a URI matcher that the AlimentContentProvider uses */
        UriMatcher testMatcher = AlimentContentProvider.buildUriMatcher();

        /* Test that the code returned from our matcher matches the expected Aliments int */
        String tasksUriDoesNotMatch = "Error: The TASKS URI was matched incorrectly.";
        int actualAlimentsMatchCode = testMatcher.match(TEST_ALIMENTS);
        int expectedAlimentsMatchCode = AlimentContentProvider.ALIMENTS;
        assertEquals(tasksUriDoesNotMatch,
                actualAlimentsMatchCode,
                expectedAlimentsMatchCode);

        /* Test that the code returned from our matcher matches the expected ALIMENTS_WITH_ID */
        String taskWithIdDoesNotMatch =
                "Error: The ALIMENT_WITH_ID URI was matched incorrectly.";
        int actualAlimentWithIdCode = testMatcher.match(TEST_ALIMENTS_WITH_ID);
        int expectedAlimentWithIdCode = AlimentContentProvider.ALIMENTS_WITH_ID;
        assertEquals(taskWithIdDoesNotMatch,
                actualAlimentWithIdCode,
                expectedAlimentWithIdCode);
    }


}
