package pl.annawyszomirskaszmyd.farmerspring.models.mappers;

public interface Mapper<K, V> {
    public V map(K key);
}
