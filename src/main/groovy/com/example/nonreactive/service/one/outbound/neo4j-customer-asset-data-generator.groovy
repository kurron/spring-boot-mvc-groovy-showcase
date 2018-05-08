#!/usr/bin/env groovy

package com.example.nonreactive.service.one.outbound

import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.atomic.AtomicInteger

println 'This script will generate CSV files that can be imported into Neo4J'

// generate 1000 customers
// generate 10000 assets
// generate 100000 random associations between customer and asset

def counter = new AtomicInteger()

// Use one of int, long, float, double, boolean, byte, short, char, string
// id:ID, :LABEL, username:string
def data = (1..1000).collect {
    def random = ThreadLocalRandom.current()
    def hexString = Integer.toHexString( random.nextInt( Integer.MAX_VALUE ) )
    "${counter.incrementAndGet()},Customer,${hexString}"
}
def customers = [] + data

def lines = ['id:ID,:LABEL,username:string'] + data

def nodes = new File('customers.csv')
nodes.withWriter('utf-8') { writer ->
    lines.each {
        writer.writeLine( it as String )
    }
}

println( 'Customer CSV is customers.csv')

data = (1..10000).collect {
    def random = ThreadLocalRandom.current()
    def hexString = Integer.toHexString( random.nextInt( Integer.MAX_VALUE ) )
    "${counter.incrementAndGet()},Asset,${hexString}"
}
def assets = [] + data

lines = ['id:ID,:LABEL,name:string'] + data

nodes = new File('assets.csv')
nodes.withWriter('utf-8') { writer ->
    lines.each {
        writer.writeLine( it as String )
    }
}

println( 'Asset CSV is assets.csv')

// :START_ID, times:int, :END_ID, :TYPE
data = (1..1000).collect {
    def random = ThreadLocalRandom.current()
    def from = customers.get( random.nextInt( customers.size() ) )
    def to = assets.get( random.nextInt( assets.size() ) )
    def times = random.nextInt( 255 )
    def type = random.nextBoolean() ? 'HAS_SEEN' : 'HAS_CLICKED'
    "${from.split( ',' ).first()},${times},${to.split( ',' ).first()},${type}"
}
lines = [':START_ID,times:int,:END_ID,:TYPE'] + data

def relationships = new File('relationships.csv')
relationships.withWriter('utf-8') { writer ->
    lines.each {
        writer.writeLine( it as String )
    }
}

println( 'Relationships CSV is relationships.csv')
