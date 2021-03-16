package repository;

import domain.*;
import validation.*;

public class NotaRepository extends AbstractCRUDRepository<Pair<Integer, Integer>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
