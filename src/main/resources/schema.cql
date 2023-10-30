-- Create UDT for Ingredients
CREATE TYPE IF NOT EXISTS IngredientUDT (
    name text,
    type text
);

-- Create Ingredient table
CREATE TABLE IF NOT EXISTS Ingredient (
    id text PRIMARY KEY,
    name text,
    type text
);

-- Create Taco Order table
CREATE TABLE IF NOT EXISTS Taco_Order (
    id UUID PRIMARY KEY,
    delivery_name text,
    delivery_street text,
    delivery_city text,
    delivery_state text,
    delivery_zip text,
    cc_number text,
    cc_expiration text,
    cc_cvv text,
    placed_at timestamp
);

-- Modify Taco table to include a list of IngredientUDT
CREATE TABLE IF NOT EXISTS Taco (
    id UUID PRIMARY KEY,
    name text,
    taco_order UUID,
    created_at timestamp,
    ingredients list<frozen<IngredientUDT>>  
);

-- Create Ingredient_Ref table
CREATE TABLE IF NOT EXISTS Ingredient_Ref (
    ingredient text,  -- Changed VARCHAR(4) to text
    taco UUID,
    PRIMARY KEY (taco, ingredient)
);
