package com.stuart.dao;

import com.stuart.models.entity.ЗаписьБД;

//public interface DAO {
//    public void create(final ЭлементСправочника ЭлементСправочника);
//    public void save(final ЭлементСправочника ЭлементСправочника);
//    public void update (final ЭлементСправочника ЭлементСправочника);
//    public void delete(final ЭлементСправочника ЭлементСправочника);
//    //public ЭлементСправочника read(final int code);
//}

public interface DAO {
    public void create(final ЗаписьБД записьБД);
    public void save();
    public void update ();
    public void delete();

    //public ЭлементСправочника read(final int code);
}
