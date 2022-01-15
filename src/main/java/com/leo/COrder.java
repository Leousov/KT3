package com.leo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class COrder{
    @Id
    /*@GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")*/
    @Column(name = "id", updatable = true, nullable = true)
    public UUID id;
    /*@GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")*/
    @Column(name = "uid", updatable = true, nullable = true)
    private UUID uid;
    /*@GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")*/
    @Column(name = "gid", updatable = true, nullable = true)
    private UUID gid;
    @Column(name = "datebirth", updatable = true, columnDefinition = "Date")
    private LocalDate date;

    //UID
    public UUID getUid() {
        return this.uid;
    }
    public void setUid(UUID uid) {
        this.uid = uid;
    }
    //GID
    public UUID getGid() {
        return this.gid;
    }
    public void setGid(UUID gid) {
        this.gid = gid;
    }
    //DATE
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public COrder(UUID id, UUID user_id, UUID good_id, LocalDate date){
        setId(id);
        setDate(date);
        setGid(good_id);
        setUid(user_id);
    }
    public COrder(){
        setId(UUID.randomUUID());
        setUid(null);
        setGid(null);
        setDate(LocalDate.now());

    }
}