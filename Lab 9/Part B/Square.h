#ifndef SQUARE_H
#define SQUARE_H

class Square : public Shape {
private:
	double side_a;
public:
	Square(char* shapeName, double x, double y, double side);
	~Square();
	Square(Square& s);

	double area();
	double perimeter();

	double getSideA();
	void setSideA(double side);

	void display();
};

#endif