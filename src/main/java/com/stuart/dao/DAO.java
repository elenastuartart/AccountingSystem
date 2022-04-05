package com.stuart.dao;

import com.stuart.models.entity.справочники.ЭлементСправочника;

public interface DAO {
    public void create(final ЭлементСправочника ЭлементСправочника);
    public void save(final ЭлементСправочника ЭлементСправочника);
    public void update (final ЭлементСправочника ЭлементСправочника);
    public void delete(final ЭлементСправочника ЭлементСправочника);
    //public ЭлементСправочника read(final int code);
}
