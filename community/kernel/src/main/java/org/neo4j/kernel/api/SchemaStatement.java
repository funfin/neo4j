/**
 * Copyright (c) 2002-2013 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.api;

import org.neo4j.kernel.api.constraints.UniquenessConstraint;
import org.neo4j.kernel.api.exceptions.schema.AddIndexFailureException;
import org.neo4j.kernel.api.exceptions.schema.AlreadyConstrainedException;
import org.neo4j.kernel.api.exceptions.schema.AlreadyIndexedException;
import org.neo4j.kernel.api.exceptions.schema.CreateConstraintFailureException;
import org.neo4j.kernel.api.exceptions.schema.DropConstraintFailureException;
import org.neo4j.kernel.api.exceptions.schema.DropIndexFailureException;
import org.neo4j.kernel.impl.api.index.IndexDescriptor;

public class SchemaStatement extends ReadStatement implements SchemaWrite
{
    SchemaStatement( KernelTransactionImplementation transaction, Statement statement )
    {
        super( transaction, statement );
    }

    // <SchemaWrite>
    @Override
    public IndexDescriptor indexCreate( long labelId, long propertyKeyId )
            throws AddIndexFailureException, AlreadyIndexedException, AlreadyConstrainedException
    {
        assertOpen();
        return schemaWrite().indexCreate( state, labelId, propertyKeyId );
    }

    @Override
    public void indexDrop( IndexDescriptor descriptor ) throws DropIndexFailureException
    {
        assertOpen();
        schemaWrite().indexDrop( state, descriptor );
    }

    @Override
    public UniquenessConstraint uniquenessConstraintCreate( long labelId, long propertyKeyId )
            throws CreateConstraintFailureException, AlreadyConstrainedException, AlreadyIndexedException
    {
        assertOpen();
        return schemaWrite().uniquenessConstraintCreate( state, labelId, propertyKeyId );
    }

    @Override
    public void constraintDrop( UniquenessConstraint constraint ) throws DropConstraintFailureException
    {
        assertOpen();
        schemaWrite().constraintDrop( state, constraint );
    }
    // </SchemaWrite>
}
