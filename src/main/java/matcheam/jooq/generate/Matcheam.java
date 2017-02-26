/*
 * This file is generated by jOOQ.
*/
package matcheam.jooq.generate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import matcheam.jooq.generate.tables.Match;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Matcheam extends SchemaImpl {

    private static final long serialVersionUID = -1880646567;

    /**
     * The reference instance of <code>matcheam</code>
     */
    public static final Matcheam MATCHEAM = new Matcheam();

    /**
     * The table <code>matcheam.match</code>.
     */
    public final Match MATCH = matcheam.jooq.generate.tables.Match.MATCH;

    /**
     * No further instances allowed
     */
    private Matcheam() {
        super("matcheam", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Match.MATCH);
    }
}
