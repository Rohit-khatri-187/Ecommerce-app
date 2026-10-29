import { Clock, Mail, MapPin, Phone } from "lucide-react";
import React from "react";

type ContactItem = {
  title: string;
  subtitle: string;
  icon: React.ReactNode;
};

const contactData: ContactItem[] = [
  {
    title: "Visit us",
    subtitle: "Kathmandu, Nepal",
    icon: (
      <MapPin className="h-6 w-6 text-gray-600 group-hover:text-primary transition-colors" />
    ),
  },
  {
    title: "Call us",
    subtitle: "+123 456 7890",
    icon: (
      <Phone className="h-6 w-6 text-gray-600 group-hover:text-primary transition-colors" />
    ),
  },
  {
    title: "Working hours",
    subtitle: "Mon-Sat: 9am-6pm",
    icon: (
      <Clock className="h-6 w-6 text-gray-600 group-hover:text-primary transition-colors" />
    ),
  },
  {
    title: "Email us",
    subtitle: "support@shopkart.com",
    icon: (
      <Mail className="h-6 w-6 text-gray-600 group-hover:text-primary transition-colors" />
    ),
  },
];

const FooterTop = () => {
  return (
    <div className="grid grid-cols-2 lg:grid-cols-4 gap-8 border-b">
      {contactData.map((item, index) => (
        <div
          key={index}
          className="flex items-center gap-3 group hover:bg-gray-50 p-4 transition-colors "
        >
          {item.icon}
          <div>
            <h3 className="font-semibold text-gray-900 group-hover:text-black" >{item.title}</h3>
            <p className="text-gray-600 text-sm mt-1 group-hover:text-gray-900 " >{item.subtitle}</p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default FooterTop;
