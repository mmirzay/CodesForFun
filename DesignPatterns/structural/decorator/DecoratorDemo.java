package structural.decorator;

import structural.decorator.concreteclass.BaseReport;
import structural.decorator.decoratorClasses.FamilyColumnDecorator;
import structural.decorator.decoratorClasses.NameColumnDecorator;
import structural.decorator.interfaces.Report;

public class DecoratorDemo {
	public static void main(String[] args) {
		Report report1 = new NameColumnDecorator(new BaseReport());
		System.out.println(report1.getColumns());

		Report report2 = new FamilyColumnDecorator(
				new NameColumnDecorator(new FamilyColumnDecorator(new BaseReport())));
		System.out.println(report2.getColumns());
	}
}
