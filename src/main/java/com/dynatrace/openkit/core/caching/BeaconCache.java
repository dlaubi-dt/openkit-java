package com.dynatrace.openkit.core.caching;

import java.util.Observer;
import java.util.Set;

/**
 * Beacon Cache used to cache the Beacons generated by all sessions, actions, ...
 */
public interface BeaconCache {

    /**
     * Add an {@link Observer} which gets notified after a new event data or action data got inserted.
     *
     * @param o Observer to add.s
     */
    void addObserver(Observer o);

    /**
     * Add event data for a given {@code beaconID} to this cache.
     *
     * <p>
     * All registered observers are notified, after the event data has been added.
     * </p>
     *
     * @param beaconID The beacon's ID (aka Session ID) for which to add event data.
     * @param timestamp The data's timestamp.
     * @param data serialized event data to add.
     */
    void addEventData(Integer beaconID, long timestamp, String data);

    /**
     * Add action data for a given {@code beaconID} to this cache.
     *
     * @param beaconID The beacon's ID (aka Session ID) for which to add action data.
     * @param timestamp The data's timestamp.
     * @param data serialized action data to add.
     */
    void addActionData(Integer beaconID, long timestamp, String data);

    /**
     * Delete a cache entry for given BeaconID.
     *
     * @param beaconID The beacon's ID (aka Session ID) which to delete.
     */
    void deleteCacheEntry(Integer beaconID);

    String getNextBeaconChunk(Integer beaconID, String chunkPrefix, int maxSize, char delimiter);

    void removeChunkedData(Integer beaconID);

    void resetChunkedData(Integer beaconID);

    Set<Integer> getBeaconIDs();

    int evictRecordsByAge(Integer beaconID, long maxAge);

    int evictRecordsByNumber(Integer beaconID, int numRecords);

    long getNumBytesInCache();
}
