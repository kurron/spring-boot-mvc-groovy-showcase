#!/usr/bin/env groovy

package com.example.nonreactive.service.one.outbound

import java.util.concurrent.ThreadLocalRandom

println 'This script will generate a CSV file that can be imported into Neo4J'

// Use one of int, long, float, double, boolean, byte, short, char, string
// id:ID, :LABEL, alpha:int, bravo:long, charlie:float, delta:double, echo:boolean, foxtrot:byte, golf:short, hotel:char, india:string
def data = (1..1000).collect {
    def random = ThreadLocalRandom.current()
    def hexString = Integer.toHexString( random.nextInt( Integer.MAX_VALUE ) )
    "${it},Customer;Account,${random.nextInt()},${random.nextLong()},${random.nextFloat()},${random.nextDouble()},${random.nextBoolean()},1,2,\"X\",${hexString}"
}

def lines = ['id:ID, :LABEL, alpha:int, bravo:long, charlie:float, delta:double, echo:boolean, foxtrot:byte, golf:short, hotel:char, india:string'] + data

def nodes = new File('nodes.csv')
nodes.withWriter('utf-8') { writer ->
    lines.each {
        writer.writeLine( it as String )
    }
}

println( 'Node CSV is nodes.csv')

def fromPool = data.take( 100 )
data.removeAll( fromPool )

// :START_ID, distance:int, :END_ID, :TYPE
data = (1..1000).collect {
    def random = ThreadLocalRandom.current()
    def from = fromPool.get( random.nextInt( fromPool.size() ) )
    def to = data.get( random.nextInt( data.size() ) )
    def distance = random.nextInt( Integer.MAX_VALUE )
    "${from.split( ',' ).first()}, ${distance}, ${to.split( ',' ).first()}, CONNECTED_TO"
}
lines = [':START_ID, distance:int, :END_ID, :TYPE'] + data

def relationships = new File('relationships.csv')
relationships.withWriter('utf-8') { writer ->
    lines.each {
        writer.writeLine( it as String )
    }
}

println( 'Relationships CSV is relationships.csv')
