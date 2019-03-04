package pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.mappers;

public interface Mapper<K, V> {
    public V map(K key);
}
