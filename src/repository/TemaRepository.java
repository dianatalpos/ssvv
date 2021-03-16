package repository;

import domain.Tema;
import validation.*;

public class TemaRepository extends AbstractCRUDRepository<Integer, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}

