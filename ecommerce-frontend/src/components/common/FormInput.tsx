"use client";

import { ChangeEvent, useId, useState } from "react";
import { Eye, EyeOff, LockKeyhole, Mail, UserRound } from "lucide-react";

export type FormInputProps = {
  label: string;
  name: string;
  type?: "text" | "email" | "password";
  value: string;
  placeholder?: string;
  autoComplete?: string;
  required?: boolean;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
};

export default function FormInput({
  label,
  name,
  type = "text",
  value,
  placeholder,
  autoComplete,
  required,
  onChange,
}: FormInputProps) {
  const generatedId = useId();
  const id = `${name}-${generatedId}`;
  const [showPassword, setShowPassword] = useState(false);
  const isPassword = type === "password";

  const Icon = type === "email" ? Mail : isPassword ? LockKeyhole : UserRound;

  return (
    <div className="space-y-2">
      <label htmlFor={id} className="block text-sm font-semibold text-slate-700">
        {label}
      </label>
      <div className="relative">
        <Icon
          aria-hidden="true"
          className="absolute left-3.5 top-1/2 h-5 w-5 -translate-y-1/2 text-slate-400"
        />
        <input
          id={id}
          name={name}
          type={isPassword ? (showPassword ? "text" : "password") : type}
          value={value}
          placeholder={placeholder}
          autoComplete={autoComplete}
          required={required}
          onChange={onChange}
          className="h-12 w-full rounded-xl border border-slate-200 bg-white pl-11 pr-12 text-sm text-slate-900 outline-none transition placeholder:text-slate-400 focus:border-emerald-600 focus:ring-4 focus:ring-emerald-100"
        />
        {isPassword && (
          <button
            type="button"
            onClick={() => setShowPassword((current) => !current)}
            className="absolute right-3 top-1/2 flex h-8 w-8 -translate-y-1/2 items-center justify-center rounded-lg text-slate-400 transition hover:bg-slate-100 hover:text-slate-700 focus:outline-none focus:ring-2 focus:ring-emerald-500"
            aria-label={showPassword ? "Hide password" : "Show password"}
          >
            {showPassword ? (
              <EyeOff aria-hidden="true" className="h-5 w-5" />
            ) : (
              <Eye aria-hidden="true" className="h-5 w-5" />
            )}
          </button>
        )}
      </div>
    </div>
  );
}
